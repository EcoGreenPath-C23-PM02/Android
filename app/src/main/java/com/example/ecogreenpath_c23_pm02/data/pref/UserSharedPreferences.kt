package com.example.ecogreenpath_c23_pm02.data.pref

import android.content.Context
import android.content.SharedPreferences

object UserSharedPreferences {
    private const val PREF_NAME = "MyAppPreferences"
    private const val KEY_USER_ID = "userId"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserId(context: Context, userId: String) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
    }

    fun getUserId(context: Context): String {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(KEY_USER_ID, null).toString()
    }
}