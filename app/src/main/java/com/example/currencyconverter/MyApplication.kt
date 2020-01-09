package com.example.currencyconverter

import android.app.Application
import android.content.Context
import com.example.currencyconverter.core.injection.CoreComponent
import com.example.currencyconverter.core.injection.DaggerCoreComponent
import com.example.currencyconverter.core.log.Log

/**
 * Application configuration and initialisation.
 *
 * Responsible for initialising:
 *  - Logging
 *  - Injection core component.
 */
class MyApplication : Application() {

    internal val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.create()
    }

    override fun onCreate() {
        super.onCreate()

        Log.init()
        Log.d("onCreate")
    }

    companion object {
        /**
         * Gets the core component from the application instance.
         *
         * @return the dagger's [CoreComponent].
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MyApplication).coreComponent
    }
}
