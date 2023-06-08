package com.example.ecogreenpath_c23_pm02.ui.allTour.allActivity

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class AllActivitiesViewModel(private val repository: AppRepository) : ViewModel(){
    fun getActivitiesList() = repository.getAllActivities()
}