package com.example.currencyconverter.core

import android.app.Activity
import com.example.currencyconverter.core.injection.BaseComponent

/**
 * Base dagger component for use in activities.
 */
interface BaseActivityComponent<T : Activity> : BaseComponent<T>
