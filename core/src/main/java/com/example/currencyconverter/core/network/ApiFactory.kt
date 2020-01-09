package com.example.currencyconverter.core.network

import com.example.currencyconverter.core.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Defines an api factory. Use it to create objects to access specific APIs.
 */
abstract class ApiFactory<T> {

    /**
     * Access to the API.
     */
    abstract val api: T

    /* Default HTTP client that other API services should use. */
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(httpLogger)
        .build()

    /**
     * Retrofit instance to create the api access.
     */
    val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()

    companion object {
        /**
         * The base url for all api calls.
         */
        const val BASE_URL = "https://revolut.duckdns.org/"

        /* HTTP logging interceptor configuration. */
        private val httpLogger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
    }
}
