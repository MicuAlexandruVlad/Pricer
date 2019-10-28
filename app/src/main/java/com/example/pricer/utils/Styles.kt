package com.example.pricer.utils

import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.pricer.R

class Styles {
    companion object {
        fun storeTheme(activity: Activity) {
            activity.window.statusBarColor = ContextCompat.getColor(activity.applicationContext
                , R.color.storePrimaryDark)
        }
    }
}