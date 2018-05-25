package com.example.bakachie.jetpackproject.utils

import android.app.Activity
import android.os.Build

class ActivityUtils {
    companion object {
        fun close(activity: Activity?) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity?.finishAndRemoveTask()
            } else{
                activity?.finishAffinity()
            }
        }
    }
}