package com.example.ecogreenpath_c23_pm02.ui.quest

import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.data.response.AppRepository

class QuestViewModel(private val repository: AppRepository) : ViewModel() {
    fun getQuestList() = repository.getQuestList()
}