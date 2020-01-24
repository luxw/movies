package com.mfinatti.matheusmovies.movies.data.remote.model.extensions

import com.mfinatti.matheusmovies.movies.data.remote.model.MovieOverviewDataModel
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * Converts the network data model into the domain model.
 *
 * @return the movie overview domain model [MovieOverview].
 */
internal fun MovieOverviewDataModel.toDomainModel() =
    MovieOverview(
        id = id,
        title = title,
        poster = posterPath ?: "",
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        originalLanguage = originalLanguage,
        popularity = popularity
    )
