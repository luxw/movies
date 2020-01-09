package com.example.currencyconverter.core.injection

/**
 * Base dagger component.
 */
interface BaseComponent<T> {

    /**
     * Injects into a target.
     */
    fun inject(target: T)
}
