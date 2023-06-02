package com.example.ecogreenpath_c23_pm02.ui.kuisioner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.ui.MainActivity


class EndKuisionerFragment : Fragment() {

    private lateinit var letsGoButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_end_kuisioner, container, false)

        letsGoButton = view.findViewById(R.id.btn_lets_go)
        letsGoButton.setOnClickListener {
            // Pindah ke MainActivity
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }
}