package com.example.currencyconverter.rates.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.core.log.Log
import com.example.currencyconverter.core.rates.domain.Currency
import com.example.currencyconverter.rates.R
import com.example.currencyconverter.rates.databinding.ActivityRatesBinding
import com.example.currencyconverter.rates.injection.inject
import kotlinx.android.synthetic.main.activity_rates.rates_list
import java.util.Currency as javaCurrency
import javax.inject.Inject

/**
 * Activity entry point to the rates feature.
 */
internal class RatesActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModel: RatesViewModel

    private val ratesAdapter by lazy {
        RatesAdapter(viewModel)
    }

    private lateinit var binding: ActivityRatesBinding

    @Suppress("MagicNumber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")

        // Enables dagger injection in this class.
        inject(resources.getInteger(R.integer.requestDelay).toLong())

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rates)

        rates_list.adapter = ratesAdapter

        ratesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                // Scrolls to the topmost position, unclear why -10 works every time but 0 doesn't.
                rates_list.post { rates_list.smoothScrollToPosition(-10) }
            }
        })

        // Starting currency and value.
        val baseCurrency = Currency("EUR", 1.0)
        val baseValue = 100.0

        viewModel.loadRates(baseCurrency, baseValue)

        viewModel.ratesLiveData.observe(this, Observer { rates ->
            when (rates) {
                is RatesUiModel.Error -> Log.e("Error!")
                is RatesUiModel.Rates -> {
                    Log.d("rates: ${rates.rates}")
                    ratesAdapter.update(
                        rates.rates.map { rate ->
                            // Here would be better to cache the names and icons in two hash maps.
                            // But for the sake of simplicity I will maintain this.
                            rate.copy(
                                name = javaCurrency.getInstance(rate.acronym).displayName
                            )
                        }
                    )
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy")
    }
}
