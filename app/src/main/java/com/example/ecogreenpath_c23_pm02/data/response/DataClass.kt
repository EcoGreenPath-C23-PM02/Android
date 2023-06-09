package com.example.ecogreenpath_c23_pm02.data.response

data class RegisterRequest (
    val username: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val birth: String)

data class LoginRequest(
    val username: String,
    val password: String
)

data class UserData(
    val user_id: String,
    val username: String,
    val email: String,
    val password: String,
    val phone_number: String,
    val birth: String,
    val point: Int?,
    val firstName: String,
    val lastName: String,
    val profile_picture: String?,
    val createdAt: String
)

object pointInput{
    lateinit var questPoint: String
    lateinit var pointSuccess: String
}

data class PointRequest (
    val point: String
)

