package com.mfinatti.matheusmovies.core.injection

/**
 * Base dagger component.
 */
interface BaseComponent<T> {

    /**
     * Injects into a target.
     */
    fun inject(target: T)
}
