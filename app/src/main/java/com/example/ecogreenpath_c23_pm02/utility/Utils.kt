package com.example.ecogreenpath_c23_pm02.utility

import android.text.TextUtils
import android.view.View
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.isEmailValid(): Boolean  {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


fun View.show(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}

fun formatDate(day: Int, month: Int, year: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, day)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.YEAR, year)

    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return dateFormat.format(calendar.time)
}