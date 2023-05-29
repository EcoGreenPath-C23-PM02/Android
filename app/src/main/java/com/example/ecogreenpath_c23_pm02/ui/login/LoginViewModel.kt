package com.example.ecogreenpath_c23_pm02.ui.login

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class LoginViewModel (private val repository: AppRepository) : ViewModel(){
    fun login(email:String, password:String) = repository.login(email,password)
}