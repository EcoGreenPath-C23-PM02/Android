package com.example.ecogreenpath_c23_pm02.utility

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository
import com.example.ecogreenpath_c23_pm02.data.response.Injection
import com.example.ecogreenpath_c23_pm02.ui.allTour.allActivity.AllActivitiesViewModel
import com.example.ecogreenpath_c23_pm02.ui.allTour.allPackage.AllPackageViewModel
import com.example.ecogreenpath_c23_pm02.ui.detail.detailActivities.DetailActivityViewModel
import com.example.ecogreenpath_c23_pm02.ui.detail.detailPacket.DetailPackageViewModel
import com.example.ecogreenpath_c23_pm02.ui.home.HomeViewModel
import com.example.ecogreenpath_c23_pm02.ui.login.LoginViewModel
import com.example.ecogreenpath_c23_pm02.ui.maps.MapsViewModel
import com.example.ecogreenpath_c23_pm02.ui.profile.personalData.PersonalDataViewModel
import com.example.ecogreenpath_c23_pm02.ui.quest.QuestViewModel
import com.example.ecogreenpath_c23_pm02.ui.quest.questUpload.QuestUploadViewModel
import com.example.ecogreenpath_c23_pm02.ui.signup.RegisterViewModel

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
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PersonalDataViewModel::class.java) -> {
                PersonalDataViewModel(repository) as T
            }
            modelClass.isAssignableFrom(QuestViewModel::class.java) -> {
                QuestViewModel(repository) as T
            }
            modelClass.isAssignableFrom(QuestUploadViewModel::class.java) -> {
                QuestUploadViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AllActivitiesViewModel::class.java) -> {
                AllActivitiesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AllPackageViewModel::class.java) -> {
                AllPackageViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailActivityViewModel::class.java) -> {
                DetailActivityViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailPackageViewModel::class.java) -> {
                DetailPackageViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}