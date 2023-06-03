package com.example.ecogreenpath_c23_pm02.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("message")
    val message: LoginResult
)

data class LoginResult(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("user_id")
    val user_id: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("token")
    val token: String
)

data class GeneralResponse(
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)



data class ProfileResponse(
    @SerializedName("data")
    val data: List<ProfileData>
)

data class ProfileData(
    @SerializedName("user_id")
    val userId: String,
    val username: String,
    val email: String,
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    val birth: String,
    val point: Int?,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("profile_picture")
    val profilePicture: String?,
    @SerializedName("createdAt")
    val createdAt: String
)