package com.example.ecogreenpath_c23_pm02.ui.quest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.response.Result
import com.example.ecogreenpath_c23_pm02.databinding.ActivityQuestBinding
import com.example.ecogreenpath_c23_pm02.ui.profile.personalData.PersonalDataViewModel
import com.example.ecogreenpath_c23_pm02.ui.quest.adapter.QuestAdapter
import com.example.ecogreenpath_c23_pm02.utility.ViewModelFactory

class QuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestBinding
    private lateinit var adapter : QuestAdapter
    private val viewModel : QuestViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Quest"

        adapter = QuestAdapter()


        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@QuestActivity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        adapter.notifyDataSetChanged()

        viewModel.getQuestList().observe(this@QuestActivity){result ->
            when(result){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    val quest = result.data
                    adapter.setQuestList(quest)
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
        binding.progressBar?.visibility = if (loading) View.VISIBLE else View.GONE
    }
}