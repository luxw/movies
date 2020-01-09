package com.example.currencyconverter.core.rates.domain

/**
 * Rates as returned by the network api. A map where the key is the currency acronym and the value
 * is the current rate of a given base currency.
 *
 * @param rates a map of currency acronyms and rates.
 */
data class EndpointRates(
    val rates: Map<String, Double>
)
