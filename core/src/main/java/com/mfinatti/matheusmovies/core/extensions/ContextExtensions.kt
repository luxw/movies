package com.mfinatti.matheusmovies.core.extensions

import android.content.Context

/**
 * Gets the id based on the name of the resource.
 *
 * @param name name of the resource.
 * @param module module package which it resides.
 * @return the id of the resource.
 */
fun Context.getIdentifier(name: String, module: String) =
    resources.getIdentifier(name, "drawable", "$packageName.$module")
