package com.example.ecogreenpath_c23_pm02.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecogreenpath_c23_pm02.data.pref.UserSharedPreferences
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.databinding.FragmentHomeBinding
import com.example.ecogreenpath_c23_pm02.ui.allTour.allActivity.AllActivitiesActivity
import com.example.ecogreenpath_c23_pm02.ui.allTour.allPackage.AllPackageActivity
import com.example.ecogreenpath_c23_pm02.ui.home.Adapter.CbfAdapter
import com.example.ecogreenpath_c23_pm02.ui.home.Adapter.CfAdapter
import com.example.ecogreenpath_c23_pm02.ui.localMarket.LocalMarketActivity
import com.example.ecogreenpath_c23_pm02.ui.quest.QuestActivity
import com.example.ecogreenpath_c23_pm02.ui.socialConnect.SocialConnectActivity
import com.example.ecogreenpath_c23_pm02.utility.MlViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> {
        MlViewModelFactory.getMlInstance(requireContext())
    }

    private lateinit var cbfAdapter: CbfAdapter

    private lateinit var cfAdapter: CfAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        cbfAdapter = CbfAdapter()

        cfAdapter = CfAdapter()

        binding.apply {
            val cbfLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            activityRecyclerView.layoutManager = cbfLayoutManager
            activityRecyclerView.adapter = cbfAdapter
        }

        binding.apply {
            val cfLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            packageRecyclerView.layoutManager = cfLayoutManager
            packageRecyclerView.adapter = cfAdapter
        }

        cbfAdapter.notifyDataSetChanged()
        cfAdapter.notifyDataSetChanged()

        val userId = UserSharedPreferences.getUserId(requireContext())
        viewModel.getCbfList(userId).observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    showLoadingActivity(true)
                }
                is Result.Success -> {
                    showLoadingActivity(false)
                    val cbfList = result.data
                    cbfAdapter.setCbfList(cbfList)
                }
                is Result.Error -> {
                    showLoadingActivity(false)
                    message(result.error)
                }
            }
        }

        viewModel.getCfList(userId).observe(viewLifecycleOwner){result->
            when(result){
                is Result.Loading -> {
                    showCfLoading(true)
                }
                is Result.Success -> {
                    showCfLoading(false)
                    val cfList = result.data
                    cfAdapter.setCfList(cfList)
                }
                is Result.Error -> {
                    showCfLoading(false)
                    message(result.error)
                }
            }
        }



        binding.test1.setOnClickListener {
            val intent = Intent(requireContext(), AllPackageActivity::class.java)
            startActivity(intent)
        }
        binding.test2.setOnClickListener {
            val intent = Intent(requireContext(), AllActivitiesActivity::class.java)
            startActivity(intent)
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


    private fun showLoadingActivity(loading: Boolean){
        binding.activityProgressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun showCfLoading(loading: Boolean){
        binding.packageProgressBar.visibility = if (loading) View.VISIBLE else View.GONE
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