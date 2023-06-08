package com.example.ecogreenpath_c23_pm02.utility

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecogreenpath_c23_pm02.data.response.Injection
import com.example.ecogreenpath_c23_pm02.data.response.MlAppRepository
import com.example.ecogreenpath_c23_pm02.ui.home.HomeViewModel

class MlViewModelFactory private constructor(
    private val repository: MlAppRepository
): ViewModelProvider.NewInstanceFactory(){
    companion object{
        private var instance : MlViewModelFactory? = null
        fun getMlInstance(context: Context): MlViewModelFactory = instance ?: synchronized(this){
            instance ?: MlViewModelFactory(
                Injection.provideMlRepository(context)
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: "+ modelClass.name)
        }
    }
}