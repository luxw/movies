package com.mfinatti.matheusmovies.movies.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("SELECT *  FROM movies ORDER BY popularity DESC")
    fun a(): Single<List<MovieOverview>>
}
