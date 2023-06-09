package com.example.ecogreenpath_c23_pm02.ui.quistioner

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ecogreenpath_c23_pm02.R

class QuisionerFragment : Fragment() {
    private lateinit var answerRadioGroup: RadioGroup
    private lateinit var backButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var viewModel: QuestionnaireViewModel
    private var currentQuestionIndex = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_quisioner, container, false)

        answerRadioGroup = rootView.findViewById(R.id.answerRadioGroup)
        backButton = rootView.findViewById(R.id.btn_arrow_back)
        nextButton = rootView.findViewById(R.id.btn_arrow_next)
        questionTextView = rootView.findViewById(R.id.tv_quistion)

        viewModel = ViewModelProvider(requireActivity()).get(QuestionnaireViewModel::class.java)

        showQuestion(currentQuestionIndex)

        backButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                showQuestion(currentQuestionIndex)
            } else {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }

        nextButton.setOnClickListener {
            val checkedRadioButtonId = answerRadioGroup.checkedRadioButtonId

            if (checkedRadioButtonId != -1) {
                val selectedOptionIndex = answerRadioGroup.indexOfChild(answerRadioGroup.findViewById(checkedRadioButtonId))
                viewModel.answers[currentQuestionIndex] = selectedOptionIndex

                if (currentQuestionIndex < viewModel.questions.size - 1) {
                    currentQuestionIndex++
                    showQuestion(currentQuestionIndex)
                } else {
                    val closingFragment =
                        ClosingQuisionerFragment.newInstance(viewModel.answers.toMutableList() as ArrayList<Int?>)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, closingFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }else{
                Toast.makeText(requireContext(), "Silakan pilih Opsi", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    private fun showQuestion(index: Int) {
        answerRadioGroup.removeAllViews()

        if (index >= 0 && index < viewModel.questions.size) {
            val question = viewModel.questions[index]
            val optionList = viewModel.options[index]

            questionTextView.text = question

            val selectedDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.radio_selected)
            val notSelectedDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.radio_not_selected)
            val textSelector = ContextCompat.getColorStateList(requireContext(), R.color.radio_text_selector)
            val radioButtonHeight = resources.getDimensionPixelSize(R.dimen.radio_button_height) // Menentukan tinggi radio button
            val radioButtonPadding = resources.getDimensionPixelSize(R.dimen.radio_button_padding) // Menentukan padding radio button
            val radioButtonTextSize = resources.getDimensionPixelSize(R.dimen.radio_button_text_size) // Ukuran teks radio button
            val radioButtonLineSpacingExtra = resources.getDimensionPixelSize(R.dimen.radio_button_line_spacing_extra) // Jarak (step) di bawah teks

            for (option in optionList) {
                val radioButton = RadioButton(ContextThemeWrapper(requireContext(), R.style.RadioButtonStyle))
                radioButton.text = option
                radioButton.setButtonDrawable(android.R.color.transparent)
                radioButton.setTextColor(textSelector)
                radioButton.background = notSelectedDrawable
                radioButton.minHeight = radioButtonHeight // Mengatur tinggi minimal radio button
                radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, radioButtonTextSize.toFloat()) // Mengatur ukuran teks radio button
                radioButton.setPadding(radioButtonPadding, radioButtonPadding, radioButtonPadding, radioButtonPadding) // Mengatur padding radio button
                radioButton.setLineSpacing(radioButtonLineSpacingExtra.toFloat(), 1f) // Menambahkan jarak (step) di bawah teks

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                layoutParams.setMargins(0, 8, 0, 0)
                radioButton.layoutParams = layoutParams

                answerRadioGroup.addView(radioButton)
            }

            answerRadioGroup.setOnCheckedChangeListener(null)

            answerRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                val checkedRadioButton = group.findViewById<RadioButton>(checkedId)
                val selectedOptionIndex = group.indexOfChild(checkedRadioButton)
                viewModel.answers[index] = selectedOptionIndex

                for (i in 0 until group.childCount) {
                    val radioButton = group.getChildAt(i) as RadioButton
                    radioButton.background = if (radioButton.isChecked) selectedDrawable else notSelectedDrawable
                }
            }

            val previousAnswerIndex = viewModel.answers[index]
            if (previousAnswerIndex != null && previousAnswerIndex < answerRadioGroup.childCount) {
                val selectedRadioButton = answerRadioGroup.getChildAt(previousAnswerIndex) as RadioButton
                selectedRadioButton.isChecked = true
                selectedRadioButton.background = selectedDrawable
            } else {
                answerRadioGroup.clearCheck()
            }
        }
    }
}
