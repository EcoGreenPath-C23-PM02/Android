package com.example.ecogreenpath_c23_pm02.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import com.example.ecogreenpath_c23_pm02.R
import com.example.ecogreenpath_c23_pm02.data.pref.PreferenceDataSource
import com.example.ecogreenpath_c23_pm02.databinding.ActivityMainBinding
import com.example.ecogreenpath_c23_pm02.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pref: PreferenceDataSource by lazy {
        PreferenceDataSource.invoke(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation()

    }

    fun bottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.nav_bottom)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_maps, R.id.nav_profile
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}