package com.example.currencyconverter.rates.ui

/**
 * UI model for the rates screen.
 */
sealed class RatesUiModel {

    /**
     * Represents an error while retrieving rates.
     */
    object Error : RatesUiModel()

    /**
     * Represents the rates as calculated using the base currency and the rates given by the api.
     *
     * @param rates a list of [Rate]s.
     */
    class Rates(val rates: List<Rate>) : RatesUiModel()
}
