package com.mfinatti.matheusmovies.movies.domain.repository

import androidx.paging.PagedList
import com.mfinatti.matheusmovies.movies.data.repository.LoadingState
import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import io.reactivex.Observable

/**
 * Interface for a repository that gets movie data from different sources into the domain.
 */
internal interface MoviesRepository {

    /**
     * Gets movie overviews for user discovery.
     *
     * @param page which page to get discover movies.
     * @return an [Observable] to a list of movie overviews.
     */
    fun getDiscoverMovies(page: Int = 1): Observable<PagedList<MovieOverview>>

    /**
     * Gets the observable that emits the data loading state.
     *
     * @return an [Observable] to the current [LoadingState].
     */
    fun getLoadingStateObservable(): Observable<LoadingState>

    /**
     * Gets details of a movie.
     *
     * @param id the movie id.
     * @return an [Observable] to a [Movie].
     */
    fun getMovieDetails(id: Int): Observable<Movie>
}