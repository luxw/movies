package com.mfinatti.matheusmovies.movies.domain.usecases

import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable

internal class GetDiscoverMoviesUseCase(
    private val repository: MoviesRepository
) {

    private var currentPage = 1

    fun execute(): Observable<List<MovieOverview>> {
        return repository.getDiscoverMovies(currentPage++)
    }
}
