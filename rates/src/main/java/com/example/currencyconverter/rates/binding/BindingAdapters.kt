package com.example.currencyconverter.rates.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mfinatti.matheusmovies.core.extensions.getIdentifier
import com.example.currencyconverter.rates.R
import java.util.Locale

/**
 * Loads a profile image from a url into an [ImageView].
 *
 * @param name the name of the image. If null or unreachable then the default placeholder
 * will be loaded instead.
 */
@BindingAdapter("currencyIcon")
fun ImageView.loadImageByName(name: String?) {
    Glide.with(this)
        .load(context.getIdentifier(name?.toLowerCase(Locale.ROOT) ?: "", "rates"))
        .placeholder(R.drawable.placeholder)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}
