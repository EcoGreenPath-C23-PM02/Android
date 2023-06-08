package com.example.ecogreenpath_c23_pm02.api

import android.content.Context
import com.example.ecogreenpath_c23_pm02.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MlApiConfig {

    private lateinit var mlApiService: MlApiService

    fun getMlApiService(context: Context): MlApiService{
        if(!MlApiConfig::mlApiService.isInitialized){
            val mlRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.ML_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            mlApiService = mlRetrofit.create(MlApiService::class.java)
        }
        return mlApiService
    }


    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level =
                        HttpLoggingInterceptor.Level.BODY
                }
            })
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(AuthenticationToken(context))
            .build()
    }

}