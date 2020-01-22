package com.mfinatti.matheusmovies

import android.app.Application
import com.mfinatti.matheusmovies.core.injection.coreDataModule
import com.mfinatti.matheusmovies.core.log.Log
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application configuration and initialisation.
 *
 * Responsible for initialising:
 *  - Logging
 *  - Dependency injection
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialises Koin injection.
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(coreDataModule))
        }

        // Initialises logging.
        Log.init()

        Log.d("onCreate")
    }
}
