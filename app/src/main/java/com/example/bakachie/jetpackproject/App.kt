package com.example.bakachie.jetpackproject

import android.app.Application
import com.example.bakachie.jetpackproject.utils.Prefs

class App : Application() {

    companion object {
        var prefs : Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(this)
    }
}