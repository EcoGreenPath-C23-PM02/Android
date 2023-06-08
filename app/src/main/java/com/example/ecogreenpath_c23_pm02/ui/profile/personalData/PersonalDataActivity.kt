package com.example.ecogreenpath_c23_pm02.ui.profile.personalData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.api.ApiConfig
import com.example.ecogreenpath_c23_pm02.api.ApiService
import com.example.ecogreenpath_c23_pm02.data.pref.UserSharedPreferences
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository
import com.example.ecogreenpath_c23_pm02.data.response.ProfileData
import com.example.ecogreenpath_c23_pm02.data.response.ProfileResponse
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.databinding.ActivityPersonalDataBinding
import com.example.ecogreenpath_c23_pm02.utility.ViewModelFactory
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalDataBinding
    private val viewModel : PersonalDataViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Personal Data"

        val userId = UserSharedPreferences.getUserId(this)

        binding.apply {

            userNameEditText.isEnabled = false
            userNameEditText.isFocusable = false
            userNameEditText.isCursorVisible = false

            firstNameEditText.isEnabled = false
            firstNameEditText.isFocusable = false
            firstNameEditText.isCursorVisible = false

            lastnameEditText.isEnabled = false
            lastnameEditText.isFocusable = false
            lastnameEditText.isCursorVisible = false

            phoneNumberEditText.isEnabled = false
            phoneNumberEditText.isFocusable = false
            phoneNumberEditText.isCursorVisible = false

            birthDateEditText.isEnabled = false
            birthDateEditText.isFocusable = false
            birthDateEditText.isCursorVisible = false

            emailEditText.isEnabled = false
            emailEditText.isFocusable = false
            emailEditText.isCursorVisible = false

            passwordEditText.isEnabled = false
            passwordEditText.isFocusable = false
            passwordEditText.isCursorVisible = false

            viewModel.getUserProfile(userId).observe(this@PersonalDataActivity){result ->
                when(result){
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        //get the data
                        val profileResponse : ProfileResponse = result.data
                        val profileDataList : List<ProfileData> = profileResponse.data

                        val profileData : ProfileData? = profileDataList.firstOrNull()
                        if (profileData != null){
                            val username: String = profileData.username
                            val email: String = profileData.email
                            val password: String = profileData.password
                            val phoneNumber: String = profileData.phoneNumber
                            val birth: String = profileData.birth
                            val point: Int? = profileData.point
                            val firstName: String = profileData.firstName
                            val lastName: String = profileData.lastName
                            val profilePicture: String? = profileData.profilePicture

                            userNameEditText.setText(username)
                            firstNameEditText.setText(firstName)
                            lastnameEditText.setText(lastName)
                            phoneNumberEditText.setText(phoneNumber)
                            birthDateEditText.setText(birth)
                            emailEditText.setText(email)
                            passwordEditText.setText(password)



                            Glide.with(this@PersonalDataActivity)
                                .load(profilePicture)
                                .circleCrop()
                                .into(binding.ivProfile)
                        }

                    }

                    is Result.Error -> {
                        showLoading(false)
                        message(result.error)
                    }
                }
            }

            registButton.setOnClickListener {
                message("Coming Soon!!")
            }
        }
    }

    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        binding.pdProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}