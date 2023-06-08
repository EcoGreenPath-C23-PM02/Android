package com.example.ecogreenpath_c23_pm02.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository
import com.example.ecogreenpath_c23_pm02.data.response.MlAppRepository

class HomeViewModel(private val repository: MlAppRepository) : ViewModel() {
    fun getCbfList(id:String) = repository.getCbfList(id)

    fun getCfList(id: String) = repository.getCfList(id)
}