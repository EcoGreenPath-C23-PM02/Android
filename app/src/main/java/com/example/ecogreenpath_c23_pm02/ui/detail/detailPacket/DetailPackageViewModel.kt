package com.example.ecogreenpath_c23_pm02.ui.detail.detailPacket

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class DetailPackageViewModel(private val repository: AppRepository) : ViewModel() {
    fun getDetailPackage(id:String) = repository.getDetailPackage(id)
}