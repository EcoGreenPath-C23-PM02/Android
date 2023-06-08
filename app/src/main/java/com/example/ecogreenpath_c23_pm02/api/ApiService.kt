package com.example.ecogreenpath_c23_pm02.api

import com.example.ecogreenpath_c23_pm02.data.response.AllActivityResponse
import com.example.ecogreenpath_c23_pm02.data.response.AllPackageResponse
import com.example.ecogreenpath_c23_pm02.data.response.CbfResponse
import com.example.ecogreenpath_c23_pm02.data.response.CfResponse
import com.example.ecogreenpath_c23_pm02.data.response.GeneralResponse
import com.example.ecogreenpath_c23_pm02.data.response.LoginRequest
import com.example.ecogreenpath_c23_pm02.data.response.LoginResponse
import com.example.ecogreenpath_c23_pm02.data.response.MapsResponse
import com.example.ecogreenpath_c23_pm02.data.response.PointRequest
import com.example.ecogreenpath_c23_pm02.data.response.PointResponse
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
import retrofit2.http.PUT
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

    @GET("detail_activity")
    suspend fun getAllActivity() : AllActivityResponse

    @GET("detail_activity/{id}")
    suspend fun getDetailActivity(
        @Path("id") id: String
    ) : AllActivityResponse

    @GET("detail_package")
    suspend fun getAllPackage() : AllPackageResponse

    @GET("detail_package/{id}")
    suspend fun getDetailPackage(
        @Path("id") id: String
    ): AllPackageResponse

    @PUT("profile/{id}/point")
    suspend fun putPoint(
        @Path("id") id: String,
        @Body requestBody: PointRequest
    ) : PointResponse
}