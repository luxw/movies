package com.example.currencyconverter.rates.domain

import com.example.currencyconverter.core.rates.domain.Currency
import com.example.currencyconverter.core.rates.domain.RatesApi
import com.example.currencyconverter.core.rates.domain.RatesRepository
import io.reactivex.Single

/**
 * The repository for currency rates. Provides access to the rates API to get currency rates.
 *
 * @param ratesApi object to access the rates endpoint API.
 */
internal class RatesRepositoryImpl(private val ratesApi: RatesApi) : RatesRepository {

    override fun getRates(base: Currency): Single<List<Currency>> =
        ratesApi.getRates(base.acronym)
            .map { rates ->
                rates.rates.entries.map { entry ->
                    Currency(entry.key, entry.value)
                }
            }
}
