package com.example.ecogreenpath_c23_pm02.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class MapsViewModel(private val repository: AppRepository) : ViewModel() {
    fun getMapsList() = repository.getMapsList()

}