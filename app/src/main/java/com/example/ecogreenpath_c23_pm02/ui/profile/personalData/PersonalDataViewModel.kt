package com.example.ecogreenpath_c23_pm02.ui.profile.personalData

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class PersonalDataViewModel(private val repository: AppRepository) : ViewModel() {
    fun getUserProfile(id:String) = repository.getUserProfile(id)

    fun putPoint(id:String, point:String) =
        repository.putPoint(id,point)
}