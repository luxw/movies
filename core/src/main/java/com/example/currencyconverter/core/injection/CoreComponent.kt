package com.example.currencyconverter.core.injection

import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Core module dagger component.
 */
@Component(
    modules = [CoreDataModule::class]
)
@Singleton
interface CoreComponent {

    /**
     * Builder interface for the [CoreComponent].
     */
    @Component.Builder
    interface Builder {
        /**
         * Builds a [CoreComponent].
         */
        fun build(): CoreComponent
    }

    /**
     * Provides a Http client for Retrofit.
     */
    fun provideOkHttpClient(): OkHttpClient

    /**
     * Provides a call adapter factory for Retrofit.
     */
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory

    /**
     * Provides a converter factory for Retrofit.
     */
    fun provideConverterFactory(): MoshiConverterFactory
}
