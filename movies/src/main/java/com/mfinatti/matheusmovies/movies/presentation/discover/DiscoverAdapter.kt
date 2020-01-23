package com.mfinatti.matheusmovies.movies.presentation.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mfinatti.matheusmovies.movies.R
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * The recycler view adapter for the movie discover screen.
 */
internal class DiscoverAdapter
    : ListAdapter<MovieOverview, DiscoverAdapterViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<MovieOverview>() {

        override fun areItemsTheSame(oldItem: MovieOverview, newItem: MovieOverview) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieOverview, newItem: MovieOverview) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DiscoverAdapterViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: DiscoverAdapterViewHolder, position: Int) =
        holder.bind(getItem(position))
}