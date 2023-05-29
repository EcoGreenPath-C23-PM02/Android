package com.example.ecogreenpath_c23_pm02.api

import android.content.Context
import com.example.ecogreenpath_c23_pm02.data.pref.PreferenceDataSource
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationToken (context: Context) : Interceptor {
    private val sessionManager = PreferenceDataSource.invoke(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}