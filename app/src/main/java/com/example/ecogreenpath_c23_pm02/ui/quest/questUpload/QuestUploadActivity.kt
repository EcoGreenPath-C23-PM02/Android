package com.example.ecogreenpath_c23_pm02.ui.quest.questUpload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecogreenpath_c23_pm02.R

class QuestUploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_upload)

        supportActionBar?.title = "Do The Quest"
    }
}