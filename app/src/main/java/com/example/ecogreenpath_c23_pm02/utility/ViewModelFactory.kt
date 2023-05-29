package com.example.ecogreenpath_c23_pm02.utility

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository
import com.example.ecogreenpath_c23_pm02.data.response.Injection
import com.example.ecogreenpath_c23_pm02.ui.login.LoginViewModel
import com.example.ecogreenpath_c23_pm02.ui.maps.MapsViewModel

class ViewModelFactory private constructor(
    private val repository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideRepository(context),
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}