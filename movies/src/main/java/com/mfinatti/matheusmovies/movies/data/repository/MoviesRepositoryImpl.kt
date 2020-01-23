package com.mfinatti.matheusmovies.movies.data.repository

import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable

/**
 * Implementation of the movie repository interface.
 *
 * @param moviesApi access to the movies remote api.
 */
internal class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi
): MoviesRepository {

    override fun getDiscoverMovies(page: Int): Observable<List<MovieOverview>> {
        return moviesApi.getDiscoverMovies()
            .map { it.results }
            .flatMapObservable { movies ->
                Observable.fromArray(movies.map { movie ->
                    MovieOverview(
                        id = movie.id,
                        title = movie.title,
                        poster = movie.posterPath,
                        overview = movie.overview,
                        releaseDate = movie.releaseDate,
                        voteAverage = movie.voteAverage,
                        originalLanguage = movie.originalLanguage
                    )
                })
            }
    }
}