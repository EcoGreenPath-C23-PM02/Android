package com.example.ecogreenpath_c23_pm02.ui.quest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecogreenpath_c23_pm02.R

class QuestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        supportActionBar?.title = "Quest"
    }
}