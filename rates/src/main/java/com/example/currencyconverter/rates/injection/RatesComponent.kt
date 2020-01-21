package com.example.currencyconverter.rates.injection

import com.mfinatti.matheusmovies.core.BaseActivityComponent
import com.mfinatti.matheusmovies.core.injection.CoreComponent
import com.mfinatti.matheusmovies.core.injection.rates.RatesDataModule
import com.mfinatti.matheusmovies.core.injection.scope.FeatureScope
import com.example.currencyconverter.rates.ui.RatesActivity
import dagger.Component

/**
 * The component of the rates feature.
 */
@Component(
    modules = [RatesModule::class, RatesDataModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
internal interface RatesComponent :
    BaseActivityComponent<RatesActivity> {

    /**
     * Builder interface for the [RatesComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Builds a [RatesComponent].
         */
        fun build(): RatesComponent

        /**
         * Sets the [CoreComponent] dependency.
         */
        fun coreComponent(component: CoreComponent): Builder

        /**
         * Sets the [RatesModule] module.
         */
        fun ratesModule(module: RatesModule): Builder

        /**
         * Sets the [RatesDataModule] module.
         */
        fun ratesDataModule(module: RatesDataModule): Builder
    }
}
