package com.example.currencyconverter.rates.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyconverter.core.log.Log
import com.example.currencyconverter.rates.R

/**
 * Activity entry point to the rates feature.
 */
class RatesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")

        setContentView(R.layout.activity_rates)
    }
}
