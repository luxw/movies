package com.example.currencyconverter.rates.injection

import androidx.lifecycle.ViewModelProvider
import com.mfinatti.matheusmovies.core.injection.scope.FeatureScope
import com.example.currencyconverter.core.rates.domain.RatesRepository
import com.example.currencyconverter.rates.ui.RatesActivity
import com.example.currencyconverter.rates.ui.RatesViewModel
import com.example.currencyconverter.rates.ui.RatesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
internal class RatesModule(
    private val activity: RatesActivity,
    private val requestDelay: Long
) {

    @Provides
    fun provideContext() = activity

    @Provides
    @FeatureScope
    fun provideRatesViewModel(factory: RatesViewModelFactory): RatesViewModel =
        ViewModelProvider(activity, factory).get(RatesViewModel::class.java)

    @Provides
    @FeatureScope
    fun provideRatesViewModelFactory(ratesRepository: RatesRepository) =
        RatesViewModelFactory(requestDelay, ratesRepository)
}
