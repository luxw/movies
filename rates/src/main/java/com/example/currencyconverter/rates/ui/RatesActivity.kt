package com.example.currencyconverter.rates.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.currencyconverter.core.log.Log
import com.example.currencyconverter.core.rates.domain.Currency
import com.example.currencyconverter.rates.R
import com.example.currencyconverter.rates.injection.inject
import javax.inject.Inject

/**
 * Activity entry point to the rates feature.
 */
internal class RatesActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel: RatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")

        // Enables dagger injection in this class.
        inject(resources.getInteger(R.integer.requestDelay).toLong())

        setContentView(R.layout.activity_rates)

        val baseCurrency = Currency("EUR", 1.0)

        // Testing purposes
        @Suppress("MagicNumber")
        viewModel.getRatesLiveData(baseCurrency, 10.0).observe(this, Observer { rates ->
            when (rates) {
                is RatesUiModel.Error -> Log.e("Error!")
                is RatesUiModel.Rates -> Log.d("rates: ${rates.rates}")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy")
    }
}
