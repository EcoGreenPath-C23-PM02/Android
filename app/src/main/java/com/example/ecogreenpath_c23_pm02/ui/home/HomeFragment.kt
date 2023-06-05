package com.example.ecogreenpath_c23_pm02.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ecogreenpath_c23_pm02.databinding.FragmentHomeBinding
import com.example.ecogreenpath_c23_pm02.ui.localMarket.LocalMarketActivity
import com.example.ecogreenpath_c23_pm02.ui.quest.QuestActivity
import com.example.ecogreenpath_c23_pm02.ui.socialConnect.SocialConnectActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.test1.setOnClickListener {
            message("test")
        }
        binding.test2.setOnClickListener {
            message("test2")
        }
        binding.test3.setOnClickListener {
            val intent = Intent(requireContext(), SocialConnectActivity::class.java)
            startActivity(intent)
        }
        binding.test4.setOnClickListener {
            val intent = Intent(requireContext(), LocalMarketActivity::class.java)
            startActivity(intent)
        }
        binding.EcoQuestButton.setOnClickListener {
            val intent = Intent(requireContext(), QuestActivity::class.java)
            startActivity(intent)
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