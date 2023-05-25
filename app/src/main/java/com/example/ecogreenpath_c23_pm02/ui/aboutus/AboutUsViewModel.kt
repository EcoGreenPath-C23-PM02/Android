package com.example.ecogreenpath_c23_pm02.ui.aboutus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutUsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is About Us Fragment"
    }

    val text: LiveData<String> = _text
}