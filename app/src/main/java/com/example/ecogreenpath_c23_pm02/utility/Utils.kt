package com.example.ecogreenpath_c23_pm02.utility

import android.text.TextUtils
import android.view.View

fun String.isEmailValid(): Boolean  {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


fun View.show(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}
