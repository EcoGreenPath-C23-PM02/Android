package com.example.ecogreenpath_c23_pm02.ui.quistioner

import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.pref.UserSharedPreferences
import com.example.ecogreenpath_c23_pm02.ui.MainActivity

class ClosingQuisionerFragment : Fragment() {
    private lateinit var backButton: ImageButton
    private lateinit var letsgoButton: Button
    private lateinit var answerTextView: TextView
    private lateinit var viewModel: QuestionnaireViewModel


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_closing_quisioner, container, false)

        backButton = rootView.findViewById(R.id.btn_arrow_back)
        letsgoButton = rootView.findViewById(R.id.btn_lets_go)
        answerTextView = rootView.findViewById(R.id.tv_end_kuisioner)


        letsgoButton.setOnClickListener {
            val userId = UserSharedPreferences.getUserId(requireContext())
            val responses = mutableListOf<String>()

            for (i in viewModel.questions.indices) {
                val selectedAnswerIndex = viewModel.answers[i]

                if (selectedAnswerIndex != null) {
                    val selectedAnswer = viewModel.options[i][selectedAnswerIndex]
                    responses.add(selectedAnswer)
                }
            }

            viewModel.postQuestionnaireResponses(requireContext(), userId, responses)

            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            requireActivity().finish()
        }


        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        viewModel = ViewModelProvider(requireActivity()).get(QuestionnaireViewModel::class.java)

        arguments?.let {
            val answers = it.getIntegerArrayList(ARG_ANSWERS)
            viewModel.answers.clear()
            viewModel.answers.addAll(answers ?: mutableListOf())
        }

//        displayAnswers()

        return rootView
    }

    private fun displayAnswers() {
        val options = viewModel.options

        val answersText = viewModel.answers.mapIndexedNotNull { index, answer ->
            val optionList = options[index]
            answer?.let { optionList.getOrNull(it) }
        }.joinToString("\n")

        answerTextView.text = answersText
    }

    companion object {
        private const val ARG_ANSWERS = "answers"

        fun newInstance(answers: ArrayList<Int?>): ClosingQuisionerFragment {
            val fragment = ClosingQuisionerFragment()
            val args = Bundle()
            args.putIntegerArrayList(ARG_ANSWERS, answers)
            fragment.arguments = args
            return fragment
        }
    }
}
