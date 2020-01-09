package com.example.currencyconverter.rates.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.currencyconverter.core.rates.domain.Currency
import com.example.currencyconverter.core.rates.domain.RatesRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class RatesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val ratesRepository = mockk<RatesRepository>(relaxed = true) {
        every { getRates(baseCurrency) } returns Single.just(apiReturn)
    }

    private val observer = mockk<Observer<RatesUiModel>>(relaxed = true)

    private val ratesViewModel = RatesViewModel(100, ratesRepository)

    @Test
    fun `when getting rates, given a base currency and value, then it should emit the rates`() {
        // Given

        // When
        val liveData = ratesViewModel.getRatesLiveData(baseCurrency, baseValue)
        liveData.observeForever(observer)

        // Then
        verify {
            observer.onChanged(match { rate ->
                rate is RatesUiModel.Rates && rate.rates == listOf(Rate(apiReturn[0], baseValue))
            })
        }
    }

    @Test
    fun `when getting rates, given a base currency and value, then it should emit the rates every second`() {
        // Given

        // When
        val liveData = ratesViewModel.getRatesLiveData(baseCurrency, baseValue)
        liveData.observeForever(observer)

        // Then
        verify(atLeast = 3, timeout = 300L) {
            observer.onChanged(match { rate ->
                rate is RatesUiModel.Rates && rate.rates == listOf(Rate(apiReturn[0], baseValue))
            })
        }
    }

    private companion object {
        private val baseCurrency = Currency("EUR", 1.0)
        private val baseValue = 100.0
        private val apiReturn = listOf(Currency("USD", 1.1))
    }
}
