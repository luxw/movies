package com.mfinatti.matheusmovies.core.view.extensions

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Shows a snackbar with the error message.
 *
 * @param messageRes the string resource of the error message.
 * @param view parent view where the snack bar should be shown.
 */
fun Fragment.showErrorSnackBar(
    @StringRes messageRes: Int,
    view: View
) {
    val message = getString(messageRes)
    with(Snackbar.make(view, message, Snackbar.LENGTH_LONG)) {
        setAction(android.R.string.ok) { dismiss() }
        show()
    }
}

fun Fragment.setActionBarTitle(title: String) {
    (activity as? AppCompatActivity)?.supportActionBar?.title = title
}

fun Fragment.hideActionBar() {
    (activity as? AppCompatActivity)?.supportActionBar?.hide()
}

fun Fragment.showActionBar() {
    (activity as? AppCompatActivity)?.supportActionBar?.show()
}

