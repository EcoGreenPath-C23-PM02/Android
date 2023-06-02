package com.example.ecogreenpath_c23_pm02.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("message")
    val message: LoginResult
)

data class LoginResult(
    @field:SerializedName("status")
    val status: String,

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
