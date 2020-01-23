package com.mfinatti.matheusmovies.movies.domain.usecases

import androidx.paging.PagedList
import com.mfinatti.matheusmovies.movies.data.repository.LoadingState
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith

/**
 * A use-case to get movies for the discover screen.
 *
 * @param repository the movies repository to get the movies from.
 */
internal class GetDiscoverMoviesUseCase(
    private val repository: MoviesRepository
) {

    /**
     * Executes the use-case.
     *
     * @return an observable to a list of movies and the loading state.
     */
    fun execute(): Observable<Pair<PagedList<MovieOverview>, LoadingState>> =
        repository
            .getDiscoverMovies(1)
            .zipWith(repository.getLoadingStateObservable())
}
