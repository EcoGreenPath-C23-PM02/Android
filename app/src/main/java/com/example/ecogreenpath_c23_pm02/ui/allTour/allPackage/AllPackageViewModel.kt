package com.example.ecogreenpath_c23_pm02.ui.allTour.allPackage

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class AllPackageViewModel(private val repository: AppRepository): ViewModel(){
    fun getPackageList() = repository.getAllPackage()
}