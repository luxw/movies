package com.mfinatti.matheusmovies.core.view.binding

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Loads an image from a url address.
 *
 * @param url the address to load the image from.
 */
@BindingAdapter("image")
fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(Uri.parse(url))
        .into(this)
}