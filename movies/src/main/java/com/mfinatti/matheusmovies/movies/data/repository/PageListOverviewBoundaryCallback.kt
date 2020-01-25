package com.mfinatti.matheusmovies.movies.data.repository

import androidx.paging.PagedList
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.data.local.MoviesDao
import com.mfinatti.matheusmovies.movies.data.remote.MoviesApi
import com.mfinatti.matheusmovies.movies.data.remote.model.extensions.toDomainModel
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.injection.IO_SCHEDULER
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

internal class PageListOverviewBoundaryCallback(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val disposeBag: CompositeDisposable,
    initialPage: Int
) : PagedList.BoundaryCallback<MovieOverview>(), KoinComponent {

    private val ioScheduler: Scheduler by inject(named(IO_SCHEDULER))

    private var page = initialPage

    private var isRequestInProgress = false

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

        moviesApi.getPopularMovies(page = page)
            .subscribeOn(ioScheduler)
            .observeOn(ioScheduler)
            .map { it.results }
            .map { movieDataModels ->
                movieDataModels.map { it.toDomainModel() }
            }
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
            }
            .subscribeBy(
                onComplete = {
                    Log.d("Movies fetched and stored")
                },
                onError = { throwable ->
                    Log.e("Failed to fetch/store movies, $throwable")
                }
            )
            .addTo(disposeBag)
    }
}
