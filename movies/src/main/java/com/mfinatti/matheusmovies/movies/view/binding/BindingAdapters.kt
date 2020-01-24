package com.mfinatti.matheusmovies.movies.view.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mfinatti.matheusmovies.movies.R
import java.text.DateFormat
import java.util.Date

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

@BindingAdapter("releaseDate")
fun TextView.setReleaseDate(date: Date?) {
    date?.let {
        text = context.getString(R.string.movie_releaseDate, date)
    }
}

@BindingAdapter("score")
fun TextView.setScore(score: Double?) {
    score?.let {
        text = context.getString(R.string.movie_voteAverage, score)
    }
}

@BindingAdapter("originalTitle")
fun TextView.setOriginalTitle(title: String?) {
    title?.let {
        text = context.getString(R.string.movie_originalTitle, title)
    }
}