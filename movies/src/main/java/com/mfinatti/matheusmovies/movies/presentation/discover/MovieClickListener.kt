package com.mfinatti.matheusmovies.movies.presentation.discover

import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

internal interface MovieClickListener {

    fun onMovieClicked(movie: MovieOverview)
}