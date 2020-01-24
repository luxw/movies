package com.mfinatti.matheusmovies.movies.presentation.discover

import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.core.navigation.BaseRouter
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

internal class DiscoverRouter : BaseRouter() {

    fun goToMovieDetail(movie: MovieOverview) {
        Log.d("goToMovieDetail, id: ${movie.id}")
        val action = DiscoverFragmentDirections.openMovieDetail(movie.id, movie.title)
        navController?.navigate(action)
    }
}