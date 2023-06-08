package com.example.ecogreenpath_c23_pm02.ui.quistioner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ecogreenpath_c23_pm02.R

class QuisionerActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quisioner)

        fragmentManager = supportFragmentManager

        // Tampilkan fragment pertama (HomeFragment) saat activity pertama kali dibuat
        showHomeFragment()
    }

    private fun showHomeFragment() {
        val homeFragment = OnBoardingQuisionerFragment()
        replaceFragment(homeFragment)
    }

    fun showQuestionnaireFragment() {
        val questionnaireFragment = QuisionerFragment()
        replaceFragment(questionnaireFragment)
    }

    fun showClosingFragment() {
        val closingFragment = ClosingQuisionerFragment()
        replaceFragment(closingFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
