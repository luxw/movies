package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyconverter.core.log.Log
import com.example.currencyconverter.core.ui.Rates
import com.example.currencyconverter.core.ui.getIntentTo

/**
 * Main Activity, the only purpose is to be an entry-point to the app and route the user to the
 * first feature module.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")

        // Automatically moves to Rates module since it's the initial (and only) module.
        Log.i("Opening Rates screen")
        startActivity(getIntentTo(Rates))

        // Finishes this activity since it shouldn't be on the backstack anymore.
        finish()
    }
}
