package com.mfinatti.matheusmovies.movies.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.data.local.MoviesDao
import com.mfinatti.matheusmovies.movies.data.remote.MoviesApi
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

/**
 * Implementation of the movie repository interface.
 *
 * @param moviesApi access to the movies remote api.
 */
internal class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao
): MoviesRepository {

    private val progressStateObservable = BehaviorSubject.create<LoadingState>()

    override fun getDiscoverMovies(page: Int): Observable<PagedList<MovieOverview>> {
        Log.d("getDiscoverMovies, page: $page")

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(INITIAL_LOAD_HINT)
            .setPageSize(PAGE_SIZE)
            .build()

//        moviesDao.a().subscribeOn(Schedulers.io()).subscribe { movies, t ->
//            Log.d("movies ${movies.size}")
//            Log.d("movies: ${movies.map { it.title }}")
//        }

        return RxPagedListBuilder(moviesDao.getOverviews(), config)
            .setBoundaryCallback(
                PageListOverviewBoundaryCallback(
                    moviesApi,
                    moviesDao,
                    progressStateObservable,
                    page
                )
            )
            .buildObservable()
    }

    override fun getLoadingStateObservable() = progressStateObservable

    private companion object {
        private const val PAGE_SIZE = 20
        private const val INITIAL_LOAD_HINT = 40
    }
}
