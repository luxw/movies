package com.mfinatti.matheusmovies.movies.data.remote.model

import com.squareup.moshi.Json

/**
 * Models the discover api call result.
 */
data class DiscoverResponseDataModel(
    val page: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int,
    val results: List<MovieOverviewDataModel>
)
