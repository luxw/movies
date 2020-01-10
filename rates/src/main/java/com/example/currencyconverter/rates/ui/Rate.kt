package com.example.currencyconverter.rates.ui

import com.example.currencyconverter.core.rates.domain.Currency

/**
 * Represents a rate no the UI. Which is all data that can be inferred from the given rates and
 * the base currency value.
 *
 * @param name the full name of the currency. E.g.: US Dollar.
 * @param acronym the currency acronym. E.g.: USD.
 * @param value the currency value after it's converted from the base currency.
 * @param rate the actual rate of conversion from the base currency.
 */
data class Rate(
    val name: String,
    val acronym: String,
    val value: Double,
    val rate: Double
) {

    /**
     * Creates a [Rate] object based on a [Currency] object.
     *
     * @param currency a [Currency] object.
     * @param baseValue the value of the base currency. So the [value] can be calculated.
     */
    constructor(currency: Currency, baseValue: Double) :
            this("", currency.acronym, currency.rate * baseValue, currency.rate)

    /**
     * Transforms into the currency object related to this rate.
     */
    fun toCurrency() = Currency(acronym, 1.0)
}
