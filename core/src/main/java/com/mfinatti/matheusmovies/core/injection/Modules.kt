package com.mfinatti.matheusmovies.core.injection

import com.mfinatti.matheusmovies.core.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

/**
 * Core data module.
 * Contains the dependencies used for data modules, such as HTTP Clients, converters and adapters.
 */
val coreDataModule = module {

    // HTTP Logging interceptor for OkHttp clients.
    factory {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    // OkHttp client.
    factory {
        OkHttpClient.Builder()
        .addInterceptor(get<HttpLoggingInterceptor>())
        .build()
    }

    // RxJavaCallAdapter.
    factory {
        RxJava2CallAdapterFactory.create()
    }

    // Moshi converter.
    factory {
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .add(KotlinJsonAdapterFactory())
                .build()
        )
    }
}
