package com.example.currencyconverter.core.rates.domain

/**
 * Represents a currency from the endpoint.

 * @param acronym the currency's acronym. E.g.: EUR
 * @param rate the current rate of the currency (given another currency as base). 1 if it's the base
 * currency.
 *
 * Note: This could possibly be replaced by [android.icu.util.Currency] on API 24+. But for the sake
 * of simplicity of this small project let's just use this small class.
 */
data class Currency(
    val acronym: String,
    val rate: Double
)
