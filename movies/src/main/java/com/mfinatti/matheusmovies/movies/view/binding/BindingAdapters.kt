package com.mfinatti.matheusmovies.movies.view.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mfinatti.matheusmovies.movies.R
import java.util.Date

/**
 * Sets the genre.
 */
@BindingAdapter("genre")
fun TextView.setGenresString(list: List<String>?) {
    list?.let {
        text = context
            .resources
            .getQuantityString(
                R.plurals.movie_genres,
                list.size,
                list.joinToString(", ")
            )
    }
}

/**
 * Sets the release date.
 */
@BindingAdapter("releaseDate")
fun TextView.setReleaseDate(date: Date?) {
    date?.let {
        text = context.getString(R.string.movie_releaseDate, date)
    }
}

/**
 * Sets the score.
 */
@BindingAdapter("score")
fun TextView.setScore(score: Double?) {
    score?.let {
        text = context.getString(R.string.movie_voteAverage, score)
    }
}

/**
 * Sets the original title.
 */
@BindingAdapter("originalTitle")
fun TextView.setOriginalTitle(title: String?) {
    title?.let {
        text = context.getString(R.string.movie_originalTitle, title)
    }
}
