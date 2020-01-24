package com.mfinatti.matheusmovies.movies.data.remote

import com.mfinatti.matheusmovies.movies.data.remote.model.DiscoverResponseDataModel
import com.mfinatti.matheusmovies.movies.data.remote.model.MovieDetailResponseDataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API access to the TVdb.
 */
internal interface MoviesApi {

    /**
     * Gets the popular movies.
     *
     * @param page Specify the page of results to query. default: 1.
     *
     * @return a [Single] observable with the result of the query in a [DiscoverResponseDataModel]
     * object.
     */
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int = 1
    ): Single<DiscoverResponseDataModel>

    /**
     * Gets details for a movie.
     *
     * @param id the id of the movie.
     *
     * @return a [Single] observable with the result fo the query in a
     * [MovieDetailResponseDataModel] object.
     */
    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") id: Int
    ): Single<MovieDetailResponseDataModel>
}
