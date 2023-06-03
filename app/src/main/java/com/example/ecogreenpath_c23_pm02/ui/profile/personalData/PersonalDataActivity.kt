package com.example.ecogreenpath_c23_pm02.ui.profile.personalData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.api.ApiConfig
import com.example.ecogreenpath_c23_pm02.api.ApiService
import com.example.ecogreenpath_c23_pm02.data.pref.UserSharedPreferences
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository
import com.example.ecogreenpath_c23_pm02.data.response.Profile
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.databinding.ActivityPersonalDataBinding
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalDataBinding
    private lateinit var userRepository: AppRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService(this)
        userRepository = AppRepository(apiService)

        val userId = UserSharedPreferences.getUserId(this)

        CoroutineScope(Dispatchers.Main).launch {
            when (val userProfileResult = userRepository.getUserProfile(userId)){
                is Result.Success -> {
                    val userProfile = userProfileResult.data

                    binding.apply {
                        userNameEditText.setText(userProfile.username)
                        firstNameEditText.setText(userProfile.firstName)
                        lastnameEditText.setText(userProfile.lastName)
                        phoneNumberEditText.setText(userProfile.phone_number)
                        birthDateEditText.setText(userProfile.birth)
                        emailEditText.setText(userProfile.email)
                        passwordEditText.setText(userProfile.password)


                        println(userProfile.username)
                    }
                }
                is Result.Error -> {
                    val errorMsg = userProfileResult.error
                }
                else -> {}
            }
        }
    }
}