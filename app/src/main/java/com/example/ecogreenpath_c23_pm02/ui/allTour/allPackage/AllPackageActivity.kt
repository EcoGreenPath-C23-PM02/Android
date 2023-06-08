package com.example.ecogreenpath_c23_pm02.ui.allTour.allPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.databinding.ActivityAllPackageBinding
import com.example.ecogreenpath_c23_pm02.ui.allTour.allPackage.adapter.AllPackageAdapter
import com.example.ecogreenpath_c23_pm02.utility.ViewModelFactory

class AllPackageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllPackageBinding
    private lateinit var adapter: AllPackageAdapter
    private val viewModel by viewModels<AllPackageViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllPackageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Package List"

        adapter = AllPackageAdapter()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@AllPackageActivity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        adapter.notifyDataSetChanged()

        viewModel.getPackageList().observe(this@AllPackageActivity){result ->
            when(result){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    val allPackage = result.data
                    adapter.setAllPackageList(allPackage)
                }
                is Result.Error -> {
                    showLoading(false)
                    message(result.error)
                }
            }
        }


    }

    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading(loading: Boolean){
        binding.progressBar4.visibility = if (loading) View.VISIBLE else View.GONE
    }
}