package com.example.currencyconverter.rates.ui

import com.example.currencyconverter.core.rates.domain.Currency

/**
 * Represents a rate no the UI. Which is all data that can be inferred from the given rates and
 * the base currency value.
 *
 * @param name the full name of the currency. E.g.: US Dollar.
 * @param acronym the currency acronym. E.g.: USD.
 * @param value the currency value after it's converted from the base currency.
 * @param icon the currency icon.
 */
data class Rate(
    val name: String,
    val acronym: String,
    val value: Double,
    val icon: String
) {

    /**
     * Creates a [Rate] object based on a [Currency] object.
     *
     * @param currency a [Currency] object.
     * @param baseValue the value of the base currency. So the [value] can be calculated.
     */
    constructor(currency: Currency, baseValue: Double) :
            this("", currency.acronym, currency.rate * baseValue, "")
}
