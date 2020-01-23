package com.mfinatti.matheusmovies.movies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Models the overview of a movie, without many details.
 *
 * @param id the movie id.
 * @param title movie title.
 * @param poster url to the poster image.
 * @param overview a synopsis of the movie.
 * @param releaseDate date the movie was released.
 * @param voteAverage average of the movie score from people's votes.
 * @param originalLanguage the movie's original language.
 */
@Entity(tableName = "movies")
internal data class MovieOverview(
    @PrimaryKey val id: Int,
    val title: String,
    val poster: String,
    val overview: String,
    val releaseDate: Date,
    val voteAverage: Double,
    val originalLanguage: String,
    val popularity: Double
)