package com.example.ecogreenpath_c23_pm02.ui.localMarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecogreenpath_c23_pm02.R

class LocalMarketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_market)

        supportActionBar?.title = "Local Market"
    }
}