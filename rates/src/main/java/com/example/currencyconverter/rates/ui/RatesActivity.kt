package com.example.currencyconverter.rates.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyconverter.core.log.Log
import com.example.currencyconverter.core.network.ApiFactory
import com.example.currencyconverter.core.rates.domain.RatesApi
import com.example.currencyconverter.rates.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Activity entry point to the rates feature.
 */
internal class RatesActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")

        setContentView(R.layout.activity_rates)

        // Testing purposes.
        val ratesRepo = object : ApiFactory<RatesApi>() {
            override val api: RatesApi
                get() = retrofit.create(RatesApi::class.java)
        }.api

        // Testing purposes.
        val disposable = ratesRepo.getRates("EUR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, error ->
                Log.d("error: $error")
                Log.d("result: $result")
            }

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy")
        compositeDisposable.clear()
    }
}
