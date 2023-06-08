package com.example.ecogreenpath_c23_pm02.ui.quistioner

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.ui.MainActivity

class ClosingQuisionerFragment : Fragment() {
    private lateinit var backButton: ImageButton
    private lateinit var letsgoButton: Button
    private lateinit var answerTextView: TextView
    private lateinit var viewModel: QuestionnaireViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_closing_quisioner, container, false)

        backButton = rootView.findViewById(R.id.btn_arrow_back)
        letsgoButton = rootView.findViewById(R.id.btn_lets_go)
        answerTextView = rootView.findViewById(R.id.tv_end_kuisioner)

        letsgoButton.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        viewModel = ViewModelProvider(requireActivity()).get(QuestionnaireViewModel::class.java)

        arguments?.let {
            val answers = it.getIntegerArrayList(ARG_ANSWERS)
            viewModel.answers.clear()
            viewModel.answers.addAll(answers ?: mutableListOf(null, null, null,null,null))
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
