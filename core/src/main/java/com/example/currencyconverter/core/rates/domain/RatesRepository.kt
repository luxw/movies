package com.example.currencyconverter.core.rates.domain

import io.reactivex.Single

/**
 * Represents a repository for the currency conversion rates and the necessary functions to obtain
 * them.
 */
interface RatesRepository {

    /**
     * Gets an observable to currency conversion rates given a base currency.
     *
     * @param base the base currency. E.g. if the base currency is EUR then the result will be a
     * list of all the other currencies and their rates.
     */
    fun getRates(base: Currency): Single<List<Currency>>
}
