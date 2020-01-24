package com.mfinatti.matheusmovies.movies.data.remote.model

import com.squareup.moshi.Json
import java.util.Date

/**
 * Models the data received from the network api/database for movie details.
 */
data class MovieDetailResponseDataModel(
    val id: Int,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String?,
    val popularity: Double,
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
    val overview: String,
    @Json(name = "release_date") val releaseDate: Date,
    val homepage: String,
    val budget: Long,
    val revenue: Long,
    val status: String,
    val tagline: String,
    val genres: List<GenreDataModel>
)
