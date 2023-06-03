package com.example.ecogreenpath_c23_pm02.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.pref.PreferenceDataSource
import com.example.ecogreenpath_c23_pm02.databinding.FragmentProfileBinding
import com.example.ecogreenpath_c23_pm02.ui.aboutus.AboutActivity
import com.example.ecogreenpath_c23_pm02.ui.aboutus.AboutUsFragment
import com.example.ecogreenpath_c23_pm02.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val pref : PreferenceDataSource by lazy {
        PreferenceDataSource.invoke(requireContext())
    }

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        binding.personalDataButton.setOnClickListener{
            message("This is personal data")
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

    private fun message(message:String){
        val context = requireContext().applicationContext
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}