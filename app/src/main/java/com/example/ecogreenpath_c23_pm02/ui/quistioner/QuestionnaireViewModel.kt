package com.example.ecogreenpath_c23_pm02.ui.quistioner

import androidx.lifecycle.ViewModel

class QuestionnaireViewModel : ViewModel() {
    val answers: MutableList<Int?> = mutableListOf()
    val questions = listOf(
        "What activities do you prefer to do?",
        "How do you usually travel for ecotourism?",
        "What is your preferred mode of transportation for ecotourism?",
        "Which of the following activities are you interested in?",
        "Which packet tour are you interested in?"
    )
    val options = listOf(
        listOf("Tracking", "Cycling", "Conservation", "Cooking", "Packet Tour"),
        listOf("Solo", "Group tour"),
        listOf("Walking", "Biking", "Car rental", "Public transportation"),
        listOf("Rice Field Tracking", "Jungle Trekking", "Taro Village Cycling Tour", "Kintamani Down Hill Cycling", "Fireflies Watching", "Cooking Experience", "Tri Hita Karana Journey"),
        listOf("3D 2N Authentic Rural Experience at Tegal dukuh Camp", "Hiking Journey In Taro Village", "Treasure Hunt & Cooking Battle", "Camping Into The Wild")
    )


    init {
        answers.addAll(List(questions.size) { null })
    }
}
