package com.example.ecogreenpath_c23_pm02.ui.kuisioner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.example.ecogreenpath_c23_pm02.R

@Suppress("DEPRECATION")
class KuisionerActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuisioner)

        supportActionBar?.hide()


        viewPager = findViewById(R.id.viewPager)
        nextButton = findViewById(R.id.btn_arrow_next)
        backButton = findViewById(R.id.btn_arrow_back)

        backButton.visibility = View.GONE

        val adapter = KuisionerPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Tidak ada tindakan yang perlu dilakukan di sini
            }

            override fun onPageSelected(position: Int) {
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

            override fun onPageScrollStateChanged(state: Int) {
                // Tidak ada tindakan yang perlu dilakukan di sini
            }
        })

        nextButton.setOnClickListener {
            val currentItem = viewPager.currentItem
            val nextItem = currentItem + 1
            viewPager.setCurrentItem(nextItem, true)
        }

        backButton.setOnClickListener {
            val currentItem = viewPager.currentItem
            val previousItem = currentItem - 1
            viewPager.setCurrentItem(previousItem, true)
        }
    }
}