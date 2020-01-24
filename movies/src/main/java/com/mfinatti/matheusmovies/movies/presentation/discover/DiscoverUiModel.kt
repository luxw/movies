package com.mfinatti.matheusmovies.movies.presentation.discover

import androidx.paging.PagedList
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * Ui model for communication between ViewModel and the View.
 */
internal sealed class DiscoverUiModel {

    /**
     * Movies loaded.
     *
     * @param movies a paged list of movie overviews.
     */
    internal class Loaded(val movies: PagedList<MovieOverview>) : DiscoverUiModel()

    /**
     * Error.
     */
    internal object Error : DiscoverUiModel()
}
