package com.example.pricer.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.example.pricer.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.floor

class Styles {
    companion object {
        const val TAG = "Styles"

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

    val array = colorArray()

    fun setRandomColor(card: CardView, initial: TextView) {
        val index = generateRandomNumber(array.size)
        Log.d(TAG, "setRandomColor: index -> $index")

        if (index <= 25) {
            // black text
            initial.setTextColor(Color.BLACK)
        } else {
            // white text
            initial.setTextColor(Color.WHITE)
        }

        card.setCardBackgroundColor(Color.parseColor(array[index]))
    }

    private fun generateRandomNumber(max: Int): Int {
        val v = floor(Math.random() * max).toInt()
        Log.d(TAG, "generateRandomNumber:")
        Log.d(TAG, "generateRandomNumber: rand val -> $v")
        return v
    }

    private fun colorArray(): ArrayList<String> {
        Log.d(TAG, "colorArray:")
        return ArrayList<String>().also {
            //for black text

            it.add("#FFB238")
            it.add("#FFBF46")
            it.add("#AAAAAA")
            it.add("#FF8A5B")
            it.add("#FCEADE")
            it.add("#F4E9CD")
            it.add("#DEB986")
            it.add("#7DDF64")
            it.add("#EFD3D7")
            it.add("#8FBFE0")
            it.add("#F7B538")
            it.add("#5CC8FF")
            it.add("#E7BB41")
            it.add("#E1E6E1")
            it.add("#ECC30B")
            it.add("#84BCDA")
            it.add("#DCF763")
            it.add("#42CAFD")
            it.add("#C0DA74")
            it.add("#FCDC4D")
            it.add("#BFB7B6")
            it.add("#97CC04")
            it.add("#ABD2FA")
            it.add("#EB9486")
            it.add("#F9F8F8")
            it.add("#EEB902")

            // for white text

            it.add("#F55536")
            it.add("#8A7968")
            it.add("#593D3B")
            it.add("#7B5E7B")
            it.add("#1C0221")
            it.add("#575761")
            it.add("#2EC4B6")
            it.add("#20A4F3")
            it.add("#F6F7F8")
            it.add("#011627")
            it.add("#321325")
            it.add("#7692FF")
            it.add("#3D518C")
            it.add("#474647")
            it.add("#F45D01")
            it.add("#2D7DD2")
            it.add("#435058")
            it.add("#5F5B6B")
            it.add("#3D3B3C")
            it.add("#067BC2")
            it.add("#F37748")
            it.add("#D56062")
            it.add("#FF7733")
            it.add("#51355A")
            it.add("#9E2B25")
            it.add("#44BBA4")
            it.add("#393E41")
            it.add("#FE5F55")
            it.add("#6E75A8")
            it.add("#8D91C7")
        }
    }
}