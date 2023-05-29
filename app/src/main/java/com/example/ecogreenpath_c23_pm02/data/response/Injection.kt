package com.example.ecogreenpath_c23_pm02.data.response

import android.content.Context
import com.example.ecogreenpath_c23_pm02.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): AppRepository {
        val apiService = ApiConfig.getApiService(context)
        return AppRepository.getInstance(apiService)
    }
}