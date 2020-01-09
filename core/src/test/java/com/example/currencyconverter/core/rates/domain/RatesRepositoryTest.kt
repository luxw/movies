package com.example.currencyconverter.core.rates.domain

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.amshove.kluent.shouldEqual

import org.junit.Test

class RatesRepositoryTest {

    private val ratesApi = mockk<RatesApi> {
        every { getRates("EUR") } returns Single.just(
            EndpointRates(mapOf("USD" to 1.1))
        )
    }

    private val ratesRepository = RatesRepository(ratesApi)

    @Test
    fun `when getting rates, given a base currency, then it should return a list of currencies and their rates`() {
        // Given
        val baseCurrency = Currency("EUR", 1.0)

        // When
        val result = ratesRepository.getRates(baseCurrency)

        // Then
        val currencies = result.blockingGet()
        currencies shouldEqual listOf(Currency("USD", 1.1))
    }
}
