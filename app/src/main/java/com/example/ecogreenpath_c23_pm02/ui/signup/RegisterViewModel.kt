package com.example.ecogreenpath_c23_pm02.ui.signup

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class RegisterViewModel (private val repository: AppRepository) : ViewModel() {
    fun register(name:String, email:String, password:String) =
        repository.register(name,email,password)
}