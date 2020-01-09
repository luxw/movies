package com.example.currencyconverter.rates.injection

import com.example.currencyconverter.MyApplication
import com.example.currencyconverter.rates.ui.RatesActivity

/**
 * Inject dependencies into [RatesActivity].
 *
 * @param requestDelay how much time between requests, in ms.
 */
internal fun RatesActivity.inject(requestDelay: Long) {
    DaggerRatesComponent.builder()
        .coreComponent(MyApplication.coreComponent(this))
        .ratesModule(RatesModule(this, requestDelay))
        .build()
        .inject(this)
}
