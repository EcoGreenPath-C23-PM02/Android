package com.example.ecogreenpath_c23_pm02.ui.detail.detailActivities

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class DetailActivityViewModel(private val repository: AppRepository) : ViewModel() {
    fun getDetailActivity(id:String) = repository.getDetailActivities(id)
}