package com.mfinatti.matheusmovies.movies.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.data.local.MoviesDao
import com.mfinatti.matheusmovies.movies.data.remote.MoviesApi
import com.mfinatti.matheusmovies.movies.data.remote.model.extensions.toDomainModel
import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Implementation of the movie repository interface.
 *
 * @param moviesApi access to the movies remote api.
 */
internal class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val disposeBag: CompositeDisposable,
    private val pagedListConfig: PagedList.Config
) : MoviesRepository {

    override fun getPopularMovies(page: Int): Observable<PagedList<MovieOverview>> {
        Log.d("getPopularMovies, page: $page")

        return RxPagedListBuilder(moviesDao.getOverviews(), pagedListConfig)
            .setBoundaryCallback(
                PageListOverviewBoundaryCallback(
                    moviesApi,
                    moviesDao,
                    disposeBag,
                    page
                )
            )
            .buildObservable()
    }

    override fun getMovieDetails(id: Int): Observable<Movie> {
        return moviesDao.getMovie(id).onErrorResumeNext {
            moviesApi.getMovie(id)
                .map { it.toDomainModel() }
                .doOnSuccess { movie -> moviesDao.insertMovie(movie) }
        }.toObservable()
    }
}
