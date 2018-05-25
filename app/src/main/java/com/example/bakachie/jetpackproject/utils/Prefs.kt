package com.example.bakachie.jetpackproject.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    private val prefsFilename = "com.example.bakachie.jetpackproject"
    private val isAuthKey = "is_auth"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename, 0)

    var token: String
        get() = prefs.getString(isAuthKey, "")
        set(value) = prefs.edit().putString(isAuthKey, value).apply()
}