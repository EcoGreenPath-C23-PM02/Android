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