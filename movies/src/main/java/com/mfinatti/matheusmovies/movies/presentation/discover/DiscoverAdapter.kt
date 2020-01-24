package com.mfinatti.matheusmovies.movies.presentation.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mfinatti.matheusmovies.movies.R
import com.mfinatti.matheusmovies.movies.databinding.ItemMovieBinding
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * The recycler view adapter for the movie discover screen.
 */
internal class DiscoverAdapter(
    private val movieClickListener: MovieClickListener
) : PagedListAdapter<MovieOverview, DiscoverAdapterViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<MovieOverview>() {

        override fun areItemsTheSame(oldItem: MovieOverview, newItem: MovieOverview) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieOverview, newItem: MovieOverview) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)

        return DiscoverAdapterViewHolder(binding, movieClickListener)
    }

    override fun onBindViewHolder(holder: DiscoverAdapterViewHolder, position: Int) =
        holder.bind(getItem(position))
}
