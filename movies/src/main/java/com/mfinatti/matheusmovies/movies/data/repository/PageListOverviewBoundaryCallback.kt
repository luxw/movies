package com.mfinatti.matheusmovies.movies.data.repository

import androidx.paging.PagedList
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.data.local.MoviesDao
import com.mfinatti.matheusmovies.movies.data.remote.MoviesApi
import com.mfinatti.matheusmovies.movies.data.remote.model.extensions.toDomainModel
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

internal class PageListOverviewBoundaryCallback(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val progress: BehaviorSubject<LoadingState>,
    initialPage: Int
) : PagedList.BoundaryCallback<MovieOverview>() {

    private var page = initialPage

    private var isRequestInProgress = false

    private val disposeBag = CompositeDisposable()

    override fun onItemAtEndLoaded(itemAtEnd: MovieOverview) {
        Log.d("onItemAtEndLoaded, $itemAtEnd")
        fetchAndStore()
    }

    override fun onZeroItemsLoaded() {
        Log.d("onZeroItemsLoaded")
        fetchAndStore()
    }

    private fun fetchAndStore() {
        // Avoid making multiple requests
        if (isRequestInProgress) {
            return
        }

        Log.d("fetchAndStore, page: $page")

        isRequestInProgress = true

        moviesApi.getDiscoverMovies(page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { it.results }
            .map { movieDataModels ->
                movieDataModels.map { it.toDomainModel() }
            }
            .doOnSubscribe { progress.onNext(LoadingState.Loading) }
            .doOnSuccess { movies ->
                Log.d("onSuccess")

                if (movies.isNotEmpty()) {
                    moviesDao.insertOverviews(movies)
                }
                page += 1
            }
            .ignoreElement()
            .doFinally {
                isRequestInProgress = false
                disposeBag.clear()
            }
            .subscribeBy(
                onComplete = {
                    Log.d("Movies fetched and stored")
                    progress.onNext(LoadingState.Success)
                },
                onError = { throwable ->
                    Log.e("Failed to fetch/store movies, $throwable")
                    progress.onNext(LoadingState.Error(throwable))
                }
            )
            .addTo(disposeBag)
    }
}
