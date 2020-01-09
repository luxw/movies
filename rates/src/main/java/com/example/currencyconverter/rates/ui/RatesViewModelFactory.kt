package com.example.currencyconverter.rates.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.core.rates.domain.RatesRepository
import javax.inject.Inject

/**
 * Factory for creating [RatesViewModel] with arguments.
 */
class RatesViewModelFactory @Inject constructor(
    private val requestDelay: Long,
    private val ratesRepository: RatesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != RatesViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return RatesViewModel(requestDelay, ratesRepository) as T
    }
}
