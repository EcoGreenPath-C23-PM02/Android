package com.example.ecogreenpath_c23_pm02.ui.quistioner


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ecogreenpath_c23_pm02.databinding.FragmentOnBoardingQuisionerBinding


class OnBoardingQuisionerFragment : Fragment() {
    private var _binding: FragmentOnBoardingQuisionerBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentOnBoardingQuisionerBinding.inflate(inflater,container,false)


        _binding!!.btnArrowNext.setOnClickListener {
            val mainActivity = activity as QuisionerActivity
            mainActivity.showQuestionnaireFragment()
        }

        return binding.root
    }
}
