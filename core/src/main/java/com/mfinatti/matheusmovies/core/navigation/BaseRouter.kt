package com.mfinatti.matheusmovies.core.navigation

import androidx.navigation.NavController
import com.mfinatti.matheusmovies.core.log.Log
import java.lang.ref.WeakReference

/**
 * Base navigation router class.
 */
open class BaseRouter {

    private var weakNavController: WeakReference<NavController>? = null

    /**
     * The activity's navigation controller.
     */
    var navController: NavController?
        get() {
            val navController = weakNavController?.get()
            if (navController == null) {
                Log.w("NavController not set")
            }
            return navController
        }
        set(value) { if (value != null) weakNavController = WeakReference(value) }

    /**
     * Goes back to previous screen by popping the current one from stack.
     */
    fun goBack() {
        Log.i("goBack")
        navController?.popBackStack()
    }
}