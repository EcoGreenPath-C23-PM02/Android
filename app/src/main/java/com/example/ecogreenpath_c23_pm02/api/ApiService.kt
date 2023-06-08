package com.example.ecogreenpath_c23_pm02.api

import com.example.ecogreenpath_c23_pm02.data.response.GeneralResponse
import com.example.ecogreenpath_c23_pm02.data.response.KuisionerResponse
import com.example.ecogreenpath_c23_pm02.data.response.LoginRequest
import com.example.ecogreenpath_c23_pm02.data.response.LoginResponse
import com.example.ecogreenpath_c23_pm02.data.response.MapsResponse
import com.example.ecogreenpath_c23_pm02.data.response.ProfileData
import com.example.ecogreenpath_c23_pm02.data.response.ProfileResponse
import com.example.ecogreenpath_c23_pm02.data.response.QuestResponse
import com.example.ecogreenpath_c23_pm02.data.response.QuestUpload
import com.example.ecogreenpath_c23_pm02.data.response.QuestUploadResponse
import com.example.ecogreenpath_c23_pm02.data.response.RegisterRequest
import com.example.ecogreenpath_c23_pm02.data.response.UserData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("register")
    suspend fun registerRequest(@Body requestBody: RegisterRequest): GeneralResponse

    @POST("login")
    suspend fun loginRequest(@Body requestBody: LoginRequest): LoginResponse

    @GET("profile/{id}")
    suspend fun getUserProfile(
        @Path("id") id : String
    ) : ProfileResponse

    @GET("quest")
    suspend fun getQuest() : QuestResponse

    @Multipart
    @POST("quest/{id}")
    suspend fun uploadImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part,
        @Part("user_desc") user_desc : RequestBody
    ) : QuestUpload

    @GET("maps")
    suspend fun getMaps() : MapsResponse

    @GET("questionnaire")
    suspend fun getKuisioner(): Response<KuisionerResponse>

}