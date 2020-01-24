package com.mfinatti.matheusmovies.movies.domain.model.extensions

import com.mfinatti.matheusmovies.movies.domain.model.Movie

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/%s"

/**
 * Url of the poster on the api.
 */
internal val Movie?.posterUrl: String
get() = IMAGE_URL.format(this?.poster ?: "")

/**
 * Url of the backdrop image on the api.
 */
internal val Movie?.backdropUrl: String
get() = IMAGE_URL.format(this?.backdrop ?: "")
