package com.example.ecogreenpath_c23_pm02.ui.kuisioner

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class KuisionerPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = listOf(
        OnboardingKuisionerFragment(),
        KuisionerFragment(),
        EndKuisionerFragment()
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun getFragment(position: Int): Fragment {
        return fragments[position]
    }
}