package com.example.ecogreenpath_c23_pm02.edittext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.example.ecogreenpath_c23_pm02.utility.isEmailValid
import com.google.android.material.textfield.TextInputEditText

class CustomEmail : TextInputEditText {

    constructor(context: Context): super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super (context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                when {
                    input.isBlank() -> error = "Please Fill This Field"
                    !input.isEmailValid()-> error = "Invalid Email"
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}