package com.example.pricer.utils

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.example.pricer.R

class Styles {
    companion object {
        fun storeTheme(activity: Activity) {
            activity.window.statusBarColor = ContextCompat.getColor(activity.applicationContext
                , R.color.storePrimaryDark)
        }

        fun getDominantColor(context: Context, imageView: ImageView): Int {
            val palette = Palette.from(imageView.drawable.toBitmap()).generate()

            return palette.getDominantColor(ContextCompat.getColor(context, R.color.storePrimaryDark))
        }
    }
}