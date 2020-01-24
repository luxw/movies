package com.mfinatti.matheusmovies.movies.presentation.discover

import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * A listener that enables actions when a movie overview is clicked.
 */
internal interface MovieClickListener {

    /**
     * Callback when a movie is clicked.
     *
     * @param movie the [MovieOverview] object that was clicked.
     */
    fun onMovieClicked(movie: MovieOverview)
}
