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

        fun getColorFromImage(context: Context, imageView: ImageView): Int {
            val palette = Palette.from(imageView.drawable.toBitmap()).generate()

            if (palette.darkVibrantSwatch == null) {
                if (palette.lightVibrantSwatch == null) {
                    if (palette.dominantSwatch != null) {
                        return palette.getDominantColor(ContextCompat.getColor(context, R.color.storePrimaryDark))
                    }
                } else {
                    return palette.getLightVibrantColor(ContextCompat.getColor(context, R.color.storePrimaryDark))
                }

            }

            return palette.getDarkVibrantColor(ContextCompat.getColor(context, R.color.storePrimaryDark))
        }
    }
}