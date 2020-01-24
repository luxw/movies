package com.mfinatti.matheusmovies.movies.domain.model.extensions

import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.presentation.details.MovieUiModel

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

/**
 * Generates a Ui model based on the domain model.
 *
 * @return a [MovieUiModel] from this movie model.
 */
internal fun Movie.toUiModel() =
    MovieUiModel(
        title = title,
        originalTitle = originalTitle,
        backdrop = backdropUrl,
        overview = overview,
        info = listOf(
            String.format("%tY", releaseDate),
            String.format("%dm", runtime),
            genres.joinToString(", ")
        ).joinToString(" | ")
    )
