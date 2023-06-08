package com.example.ecogreenpath_c23_pm02.ui.splashscreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.ecogreenpath_c23_pm02.ui.MainActivity
import com.example.ecogreenpath_c23_pm02.data.pref.PreferenceDataSource
import com.example.ecogreenpath_c23_pm02.databinding.ActivitySplashSceenBinding
import com.example.ecogreenpath_c23_pm02.ui.home.HomeFragment
import com.example.ecogreenpath_c23_pm02.ui.login.LoginActivity


@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val pref : PreferenceDataSource by lazy{
        PreferenceDataSource.invoke(this)
    }
    private lateinit var binding: ActivitySplashSceenBinding
    private val delay = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashSceenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()
        playAnimation()
        val token = pref.fetchAuthToken()
        Handler(Looper.getMainLooper()).postDelayed({
            if (token.isNullOrEmpty()) {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            }
            finish()
        }, delay)
    }

    private fun hideSystemUI() {
        supportActionBar?.hide()
    }
    private fun playAnimation(){
        val image = ObjectAnimator.ofFloat(binding.imageView2, View.ALPHA, 1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.logoText, View.ALPHA, 1f).setDuration(500)
        AnimatorSet().apply {
            playSequentially(
                image,
                title
            )
            startDelay = 200
        }.start()
    }
}