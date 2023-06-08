package com.example.ecogreenpath_c23_pm02.ui.allTour.allActivity

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.databinding.ActivityAllActivitiesBinding
import com.example.ecogreenpath_c23_pm02.ui.allTour.allActivity.Adapter.AllActivityAdapter
import com.example.ecogreenpath_c23_pm02.utility.ViewModelFactory

class AllActivitiesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllActivitiesBinding
    private lateinit var adapter: AllActivityAdapter
    private val viewModel  by viewModels<AllActivitiesViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Activity List"

        adapter = AllActivityAdapter()

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@AllActivitiesActivity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        adapter.notifyDataSetChanged()

        viewModel.getActivitiesList().observe(this@AllActivitiesActivity){result ->
            when(result){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    val allActivities = result.data
                    adapter.setActivitiesList(allActivities)
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