package com.example.currencyconverter.core.rates.domain

import io.reactivex.Single
import javax.inject.Inject

/**
 * The repository for currency rates. Provides access to the rates API to get currency rates.
 *
 * @param ratesApi object to access the rates endpoint API.
 */
class RatesRepository @Inject constructor(
    private val ratesApi: RatesApi
) {

    /**
     * Get currency rates based on a base currency. E.g. EUR.
     *
     * @param base the base currency
     *
     * @return a [Single] with the list of currencies and their rates.
     */
    fun getRates(base: Currency): Single<List<Currency>> =
        ratesApi.getRates(base.acronym)
            .map { rates ->
                rates.rates.entries.map { entry ->
                    Currency(entry.key, entry.value)
                }
            }
}
