package com.mfinatti.matheusmovies.movies.data.remote.model.extensions

import com.mfinatti.matheusmovies.movies.data.remote.model.MovieDetailResponseDataModel
import com.mfinatti.matheusmovies.movies.domain.model.Movie

/**
 * Converts the network data model into the domain model.
 *
 * @return the movie overview domain model [Movie].
 */
internal fun MovieDetailResponseDataModel.toDomainModel() =
    Movie(
        id = id,
        title = title,
        poster = posterPath ?: "",
        backdrop = backdropPath ?: "",
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        genres = genres.map { it.name },
        homepage = homepage,
        status = status,
        tagline = tagline,
        runtime = runtime
    )
