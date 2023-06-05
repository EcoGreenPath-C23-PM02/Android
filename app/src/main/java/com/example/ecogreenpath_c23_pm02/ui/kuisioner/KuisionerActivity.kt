package com.example.ecogreenpath_c23_pm02.ui.kuisioner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.ecogreenpath_c23_pm02.R

class KuisionerActivity : AppCompatActivity(), KuisionerFragment.OnAnswerSelectedListener {

    private lateinit var viewPager: ViewPager
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var adapter: KuisionerPagerAdapter
    private var isAnswerSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuisioner)
        supportActionBar?.hide()

        viewPager = findViewById(R.id.viewPager)
        nextButton = findViewById(R.id.btn_arrow_next)
        backButton = findViewById(R.id.btn_arrow_back)

        backButton.visibility = View.GONE

        adapter = KuisionerPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateButtonVisibility(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        nextButton.setOnClickListener {
            val currentFragment = adapter.getItem(viewPager.currentItem)
            if (currentFragment is KuisionerFragment) {
                if (currentFragment.isAnswerSelected) {
                    val currentItem = viewPager.currentItem
                    val nextItem = currentItem + 1
                    viewPager.setCurrentItem(nextItem, false)
                } else {
                    Toast.makeText(this, "Silakan pilih jawaban", Toast.LENGTH_SHORT).show()
                }
            } else {
                val currentItem = viewPager.currentItem
                val nextItem = currentItem + 1
                viewPager.setCurrentItem(nextItem, false)
            }
        }
        backButton.setOnClickListener {
            val currentItem = viewPager.currentItem
            val previousItem = currentItem - 1
            viewPager.setCurrentItem(previousItem, false)
        }
    }

    override fun onAnswerSelected(isSelected: Boolean) {
        isAnswerSelected = isSelected
        updateButtonVisibility(viewPager.currentItem)
    }

    private fun updateButtonVisibility(position: Int) {
        if (position == adapter.count - 1) {
            nextButton.visibility = View.GONE
        } else {
            nextButton.visibility = View.VISIBLE
        }

        if (position == 0) {
            backButton.visibility = View.GONE
        } else {
            backButton.visibility = View.VISIBLE
        }
    }
}
