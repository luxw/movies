package com.mfinatti.matheusmovies.movies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "movie_detail")
internal data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val poster: String,
    val backdrop: String,
    val originalLanguage: String,
    val originalTitle: String,
    val genres: List<String>,
    val homepage: String,
    val status: String,
    val overview: String,
    val releaseDate: Date,
    val voteAverage: Double,
    val tagline: String,
    val runtime: Int
)
