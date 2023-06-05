package com.example.ecogreenpath_c23_pm02.ui.socialConnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecogreenpath_c23_pm02.R

class SocialConnectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_connect)

        supportActionBar?.title = "Social Connect"
    }
}