package com.mfinatti.matheusmovies.movies.domain.usecases

import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable

internal class GetMovieDetailUseCase(
    private val repository: MoviesRepository
) {

    fun execute(id: Int): Observable<Movie> =
        repository.getMovieDetails(id)
}
