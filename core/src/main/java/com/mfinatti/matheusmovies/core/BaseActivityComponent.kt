package com.mfinatti.matheusmovies.core

import android.app.Activity
import com.mfinatti.matheusmovies.core.injection.BaseComponent

/**
 * Base dagger component for use in activities.
 */
interface BaseActivityComponent<T : Activity> : BaseComponent<T>
