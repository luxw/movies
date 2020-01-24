package com.mfinatti.matheusmovies.movies.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import io.reactivex.Single

/**
 * DAO interface for movies that Room will generate an object access.
 */
@Dao
internal interface MoviesDao {

    /**
     * Inserts movie overviews into the database.
     *
     * @param movies a list of [MovieOverview]s to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOverviews(movies: List<MovieOverview>)

    /**
     * Gets movie overviews from the database.
     *
     * @return a data source that can be used for paging the movies.
     */
    @Query("SELECT *  FROM movies ORDER BY popularity DESC")
    fun getOverviews(): DataSource.Factory<Int, MovieOverview>

    /**
     * Inserts a [Movie] into the database.
     *
     * @param movie the movie to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    /**
     * Gets a movie from the database.
     *
     * @param id the id of the movie.
     * @return a [Single] observable to a [Movie].
     */
    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getMovie(id: Int): Single<Movie>
}

