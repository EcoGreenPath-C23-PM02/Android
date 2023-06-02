package com.example.ecogreenpath_c23_pm02.ui.signup

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class RegisterViewModel (private val repository: AppRepository) : ViewModel() {
    fun register(username: String,
                 firstName: String,
                 lastName: String,
                 phoneNumber: String,
                 email: String,
                 password: String,
                 birth: String) =
        repository.register(username,firstName,lastName,phoneNumber,email,password,birth)
}