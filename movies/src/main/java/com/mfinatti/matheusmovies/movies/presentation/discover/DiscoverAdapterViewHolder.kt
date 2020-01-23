package com.mfinatti.matheusmovies.movies.presentation.discover

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfinatti.matheusmovies.movies.R
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview

/**
 * ViewHolder pattern for the discover adapter.
 *
 * @param view the recycler view.
 */
internal class DiscoverAdapterViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    /**
     * Binds the [movie] to the view.
     *
     * @param movie the [MovieOverview] object to bind to the view.
     */
    fun bind(movie: MovieOverview) {

        with(view) {
            findViewById<TextView>(R.id.movie_title).text = movie.title
            findViewById<TextView>(R.id.movie_overview).text = movie.overview
            findViewById<TextView>(R.id.movie_score).text = movie.voteAverage.toString()
        }

        val posterUrl = POSTER_URL.format(movie.poster)

        Glide.with(view)
            .load(Uri.parse(posterUrl))
            .into(view.findViewById(R.id.movie_poster))
    }

    private companion object {
        private const val POSTER_URL = "https://image.tmdb.org/t/p/w200/%s"
    }
}