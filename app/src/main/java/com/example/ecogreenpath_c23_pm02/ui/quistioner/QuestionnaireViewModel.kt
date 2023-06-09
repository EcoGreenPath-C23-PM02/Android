package com.example.ecogreenpath_c23_pm02.ui.quistioner

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ecogreenpath_c23_pm02.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback

import com.example.ecogreenpath_c23_pm02.data.response.QuesionerResponse
import retrofit2.Response

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
        listOf("Rice Field Tracking", "Jungle Trekking", "Taro Village Cycling Tour", "Kintamani Down Hill Cycling", "Fireflies Watching", "Cooking Experience"),
        listOf("Hiking Journey In Taro Village", "Treasure Hunt & Cooking Battle", "Camping Into The Wild")
    )


    init {
        answers.addAll(List(questions.size) { null })
    }


    fun postQuestionnaireResponses(context: Context, id: String, responses: List<String>) {
        val apiService = ApiConfig.getApiService(context)
        val requestBody = QuesionerResponse(responses)

        val call = apiService.postQuestionnaireResponses(id, requestBody)

        call.enqueue(object : Callback<QuesionerResponse> {
            override fun onResponse(call: Call<QuesionerResponse>, response: Response<QuesionerResponse>) {
                if (response.isSuccessful) {
                    // Handle successful response from the server
                    val data = response.body()?.responses
                    // ...
                } else {
                    // Handle failed response from the server
                    // ...
                }
            }

            override fun onFailure(call: Call<QuesionerResponse>, t: Throwable) {
                // Handle error while making the API call
                // ...
            }
        })
    }




}
