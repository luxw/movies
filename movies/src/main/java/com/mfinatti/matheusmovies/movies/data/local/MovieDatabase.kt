package com.mfinatti.matheusmovies.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mfinatti.matheusmovies.core.room.DateConverter
import com.mfinatti.matheusmovies.core.room.StringListConverter
import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * Room database for the movies.
 */
@Database(
    entities = [MovieOverview::class, Movie::class],
    version = 1
)
@TypeConverters(DateConverter::class, StringListConverter::class)
internal abstract class MovieDatabase : RoomDatabase() {

    /**
     * Gets the instance of the movie DAO. See [MoviesDao] for the functions which are available.
     *
     * @return an instance of the [MoviesDao].
     */
    abstract fun moviesDao(): MoviesDao
}
