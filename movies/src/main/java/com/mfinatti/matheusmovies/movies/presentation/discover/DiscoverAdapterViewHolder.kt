package com.mfinatti.matheusmovies.movies.presentation.discover

import androidx.recyclerview.widget.RecyclerView
import com.mfinatti.matheusmovies.movies.databinding.ItemMovieBinding
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * ViewHolder pattern for the discover adapter.
 *
 * @param binding the layout data binding implementation.
 */
internal class DiscoverAdapterViewHolder(
    private val binding: ItemMovieBinding,
    private val movieClickListener: MovieClickListener
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds the [movie] to the view.
     *
     * @param movie the [MovieOverview] object to bind to the view.
     */
    fun bind(movie: MovieOverview?) {
        binding.overview = movie

        movie?.let {
            binding.root.setOnClickListener { movieClickListener.onMovieClicked(movie) }
        }
    }
}
