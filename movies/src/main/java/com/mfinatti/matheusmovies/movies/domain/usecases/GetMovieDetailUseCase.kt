package com.mfinatti.matheusmovies.movies.domain.usecases

import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable

/**
 * A use-case to get movie details for the movie detail screen.
 *
 * @param repository the movies repository to get the movie from.
 */
internal class GetMovieDetailUseCase(
    private val repository: MoviesRepository
) {

    /**
     * Executes the use-case.
     *
     * @param id the id of the movie.
     * @return an [Observable] to a [Movie].
     */
    fun execute(id: Int): Observable<Movie> = repository.getMovieDetails(id)
}
