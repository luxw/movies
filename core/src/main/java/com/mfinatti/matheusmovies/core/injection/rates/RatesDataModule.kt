package com.mfinatti.matheusmovies.core.injection.rates

import com.mfinatti.matheusmovies.core.injection.scope.FeatureScope
import com.example.currencyconverter.core.rates.domain.RatesApi
import com.example.currencyconverter.core.rates.domain.RatesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Data module for the Rates feature.
 */
@Module
class RatesDataModule {

    /**
     * Provides the endpoint API access.
     */
    @Provides
    @FeatureScope
    internal fun provideRatesApi(
        client: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: MoshiConverterFactory
    ): RatesApi = Retrofit.Builder()
        .client(client)
        .baseUrl(ENDPOINT)
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .build()
        .create(RatesApi::class.java)

    /**
     * Provides the rates repository.
     */
    @Provides
    @FeatureScope
    internal fun provideRatesRepository(remoteApi: RatesApi) = RatesRepository(remoteApi)

    private companion object {
        private const val ENDPOINT = "https://revolut.duckdns.org/"
    }
}
