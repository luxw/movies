package com.mfinatti.matheusmovies.movies.domain.model.extensions

import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

private const val POSTER_URL = "https://image.tmdb.org/t/p/w200/%s"

internal val MovieOverview.posterUrl
get() = POSTER_URL.format(poster)