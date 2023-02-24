package com.samfonsec.hscards.data.api

import android.content.Context
import com.samfonsec.hscards.R
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .headers(buildHeaders())
            .build()
            .let {
                chain.proceed(it)
            }
    }

    private fun buildHeaders() = hashMapOf(
        API_KEY to context.getString(R.string.api_key),
        API_HOST to context.getString(R.string.api_host)
    ).toHeaders()

    companion object {
        private const val API_KEY = "X-RapidAPI-Key"
        private const val API_HOST = "X-RapidAPI-Host"
    }
}
