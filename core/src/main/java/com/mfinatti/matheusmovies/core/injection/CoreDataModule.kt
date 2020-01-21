package com.mfinatti.matheusmovies.core.injection

import com.example.currencyconverter.core.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Dagger module to provide core data functionality.
 */
@Module
class CoreDataModule {

    /**
     * Provides the Http client for retrofit.
     *
     * @param httpLogging the logging interceptor for the client.
     */
    @Provides
    fun provideOkHttpClient(httpLogging: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLogging)
            .build()

    /**
     * Provides the logging interceptor.
     */
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    /**
     * Provides a call adapter for retrofit.
     */
    @Provides
    @Singleton
    fun provideCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    /**
     * Provides a json adapter for retrofit.
     */
    @Provides
    @Singleton
    fun provideJsonAdapter() = KotlinJsonAdapterFactory()

    /**
     * Provides a converter factory for retrofit.
     */
    @Provides
    @Singleton
    fun provideConverterFactory(jsonAdapter: KotlinJsonAdapterFactory): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(jsonAdapter)
                .build()
        )
}
