package com.mfinatti.matheusmovies.movies.domain.repository

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
    fun getDiscoverMovies(page: Int = 1): Observable<List<MovieOverview>>
}