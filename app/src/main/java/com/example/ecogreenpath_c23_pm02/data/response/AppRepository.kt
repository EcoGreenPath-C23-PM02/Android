package com.example.ecogreenpath_c23_pm02.data.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecogreenpath_c23_pm02.api.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import kotlin.math.log

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

    fun uploadQuest(
        id: String,
        image: MultipartBody.Part,
        user_desc: RequestBody
    ): LiveData<Result<QuestUpload>> = liveData {
        emit(Result.Loading)
        try {
            val data = apiService.uploadImage(id,image,user_desc)
            emit(Result.Success(data))
        }catch (e : Exception){
            Log.d("Upload", e.message.toString())
            emit(Result.Error(e.toString()))
        }

    }


    fun getUserProfile(id:String): LiveData<Result<ProfileResponse>> = liveData{
        emit(Result.Loading)
        try {
            val data = apiService.getUserProfile(id)
            emit(Result.Success(data))
        } catch (e : Exception){
            Log.d("Profile", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getQuestList(): LiveData<Result<List<QuestList>>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.getQuest()
            val questResponse = response.data
            emit(Result.Success(questResponse))
        } catch (e: Exception) {
            Log.d("Quest", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }

    fun getMapsList() : LiveData<Result<List<MapsData>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getMaps()
            val mapsResponse = response.data
            emit(Result.Success(mapsResponse))
        }catch (e:Exception){
            Log.d("Maps", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }

    fun getAllActivities() : LiveData<Result<List<DataAllActivity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllActivity()
            val allActivityResponse = response.data
            emit(Result.Success(allActivityResponse))
        }catch (e:Exception){
            Log.d("AllActivity", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }

    fun getDetailActivities(id:String): LiveData<Result<AllActivityResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailActivity(id)
            emit(Result.Success(response))
        }catch (e:Exception){
            Log.d("DetailActivities", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }


    fun getAllPackage() : LiveData<Result<List<DataAllPackage>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllPackage()
            val allPackageResponse = response.data
            emit(Result.Success(allPackageResponse))
        }catch (e:Exception){
            Log.d("AllPackage", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }

    fun getDetailPackage(id:String): LiveData<Result<AllPackageResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailPackage(id)
            emit(Result.Success(response))
        }catch (e:Exception){
            Log.d("DetailPackage", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }

    fun putPoint(id:String, point:String): LiveData<Result<PointResponse>> = liveData {
        emit(Result.Loading)
        val pointRequestBody = PointRequest(point)
        try {
            val response = apiService.putPoint(id,pointRequestBody)
            emit(Result.Success(response))
        }catch (e:Exception){
            Log.d("Point", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }


}