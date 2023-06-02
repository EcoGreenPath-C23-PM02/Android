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

    fun login(username: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        val loginRequestBody = LoginRequest(username,password)
        emit(Result.Loading)
        try {
            val data = apiService.loginRequest(loginRequestBody)
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.d("Story Repository", "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }



    fun register(
        username: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        password: String,
        birth: String
    ): LiveData<Result<GeneralResponse>> = liveData {
        val registRequestBody = RegisterRequest(username,firstName,lastName,phoneNumber,email,password,birth)
        emit(Result.Loading)
        try {
            val data = apiService.registerRequest(registRequestBody)
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.d("Register", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }


}