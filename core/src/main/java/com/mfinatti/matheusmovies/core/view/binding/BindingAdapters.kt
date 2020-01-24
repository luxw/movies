package com.mfinatti.matheusmovies.core.view.binding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Loads an image from a url address.
 *
 * @param url the address to load the image from.
 */
@BindingAdapter("image", "placeholder", requireAll = false)
fun ImageView.loadImageFromUrl(url: String?, placeholder: Drawable?) {
    if (url != null) {
        Glide.with(this)
            .load(Uri.parse(url))
            .placeholder(placeholder)
            .into(this)
    }
}
