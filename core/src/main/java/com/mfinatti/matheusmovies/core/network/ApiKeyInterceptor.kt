package com.mfinatti.matheusmovies.core.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Intercepts the current http request to add an API key to the query parameters.
 *
 * @param apiKey the api key to be added.
 */
class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
