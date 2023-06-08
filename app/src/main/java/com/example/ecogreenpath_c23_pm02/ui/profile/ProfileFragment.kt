package com.example.ecogreenpath_c23_pm02.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.pref.PreferenceDataSource
import com.example.ecogreenpath_c23_pm02.data.pref.UserSharedPreferences
import com.example.ecogreenpath_c23_pm02.data.response.ProfileData
import com.example.ecogreenpath_c23_pm02.data.response.ProfileResponse
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.data.response.pointInput
import com.example.ecogreenpath_c23_pm02.databinding.FragmentProfileBinding
import com.example.ecogreenpath_c23_pm02.ui.aboutus.AboutActivity
import com.example.ecogreenpath_c23_pm02.ui.aboutus.AboutUsFragment
import com.example.ecogreenpath_c23_pm02.ui.login.LoginActivity
import com.example.ecogreenpath_c23_pm02.ui.profile.personalData.PersonalDataActivity
import com.example.ecogreenpath_c23_pm02.ui.profile.personalData.PersonalDataViewModel
import com.example.ecogreenpath_c23_pm02.utility.ViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val pref : PreferenceDataSource by lazy {
        PreferenceDataSource.invoke(requireContext())
    }

    private val binding get() = _binding!!

    private val viewModel: PersonalDataViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        binding.personalDataButton.setOnClickListener{
            val intent = Intent(requireContext(), PersonalDataActivity::class.java)
            startActivity(intent)
        }
        binding.settingsButton.setOnClickListener {
            message("This is settings Button")
        }
        binding.informationButton.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }
        binding.logoutButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Are you sure you want to logout?")
                .setPositiveButton("Yes"){_,_ ->
                    pref.deleteDataAuth()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("No", null)
            val alert = alertDialog.create()
            alert.show()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = UserSharedPreferences.getUserId(requireContext())

        binding.apply {
            viewModel.getUserProfile(userId).observe(viewLifecycleOwner){result ->
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

                            profileName.text = username
                            profileEmail.text = email

                            if (point == null){
                                profilePoint.text = "0"
                            }else{
                                profilePoint.text = point.toString()
                            }

                            Glide.with(this@ProfileFragment)
                                .load(profilePicture)
                                .circleCrop()
                                .into(binding.imageView4)
                        }

                    }

                    is Result.Error -> {
                        showLoading(false)
                        message(result.error)
                    }
                }
            }
        }
    }



    private fun message(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}