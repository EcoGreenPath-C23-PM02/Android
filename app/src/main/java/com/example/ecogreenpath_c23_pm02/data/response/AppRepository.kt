package com.example.ecogreenpath_c23_pm02.data.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecogreenpath_c23_pm02.api.ApiService

class AppRepository (
    private val apiService: ApiService
    ) {

    companion object {
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService
        ): AppRepository = instance ?: synchronized(this) {
            instance ?: AppRepository(apiService)
        }
    }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val data = apiService.login(email, password)
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.d("Story Repository", "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


}