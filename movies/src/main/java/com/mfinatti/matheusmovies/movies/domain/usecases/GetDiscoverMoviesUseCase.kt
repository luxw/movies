package com.mfinatti.matheusmovies.movies.domain.usecases

import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable

/**
 * A use-case to get movies for the discover screen.
 *
 * @param repository the movies repository to get the movies from.
 */
internal class GetDiscoverMoviesUseCase(
    private val repository: MoviesRepository
) {

    private var currentPage = 1

    /**
     * Executes the use-case.
     *
     * @return an observable to a list of movies.
     */
    fun execute(): Observable<List<MovieOverview>> {
        return repository.getDiscoverMovies(currentPage++)
    }
}
