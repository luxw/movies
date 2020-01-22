package com.mfinatti.matheusmovies.movies.data.model

import com.squareup.moshi.Json
import java.util.Date

/**
 * Models the data received from the network api/database.
 */
data class MovieOverviewDataModel(
    val id: Int,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String,
    val popularity: Double,
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
    val overview: String,
    @Json(name = "release_date") val releaseDate: Date
)
