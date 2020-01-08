package com.example.currencyconverter

import android.app.Application
import com.example.currencyconverter.core.log.Log

/**
 * Application configuration and initialisation.
 *
 * Responsible for initialising:
 *  - Logging
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.init()
        Log.d("onCreate")
    }
}
