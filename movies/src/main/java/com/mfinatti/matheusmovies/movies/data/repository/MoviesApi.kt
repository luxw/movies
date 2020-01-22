package com.mfinatti.matheusmovies.movies.data.repository

import com.mfinatti.matheusmovies.movies.data.model.DiscoverResponseDataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API access to the TVdb.
 */
interface MoviesApi {

    /**
     * Gets the discover movies.
     *
     * @param sortBy Choose from one of the many available sort options. Allowed Values:
     * popularity.asc, popularity.desc, release_date.asc, release_date.desc, revenue.asc,
     * revenue.desc, primary_release_date.asc, primary_release_date.desc, original_title.asc,
     * original_title.desc, vote_average.asc, vote_average.desc, vote_count.asc, vote_count.desc
     * default: popularity.desc.
     * @param language Specify a language to query translatable fields with. default: en-US.
     * @param includeAdult A filter to include or exclude adult movies. default: false.
     * @param includeVideo A filter to include or exclude videos. default: false.
     * @param page Specify the page of results to query. default: 1.
     *
     * @return a [Single] observable with the result of the query in a [DiscoverResponseDataModel]
     * object.
     */
    @GET("discover/movie")
    fun getDiscoverMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("language") language: String = "en-US",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("page") page: Int = 1
    ): Single<DiscoverResponseDataModel>
}
