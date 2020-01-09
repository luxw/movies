package com.example.currencyconverter.core.rates.domain

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the endpoint APIs to get currency rates.
 */
interface RatesApi {

    /**
     * Gets the current rates given a base currency.
     *
     * @param base the base currency acronym. E.g.: EUR for Euro, USD for US Dollar.
     *
     * @return a list of currencies with their rates.
     */
    @GET("latest")
    fun getRates(
        @Query("base") base: String
    ): Single<Rates>
}
