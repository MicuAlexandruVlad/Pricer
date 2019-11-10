package com.example.pricer.utils

import android.content.Context
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation

class AnimationUtils {
    companion object {
        fun fadeViewIn(context: Context, view: View, duration: Int) {
            view.clearAnimation()
            val fadeIn = AlphaAnimation(0F, 1F)
            fadeIn.duration = duration.toLong()
            fadeIn.interpolator = AccelerateDecelerateInterpolator()
            fadeIn.fillAfter = true
            view.startAnimation(fadeIn)
        }

        fun fadeViewOut(context: Context, view: View, duration: Int) {
            view.clearAnimation()
            val fadeOut = AlphaAnimation(1F, 0F)
            fadeOut.duration = duration.toLong()
            fadeOut.interpolator = AccelerateDecelerateInterpolator()
            fadeOut.fillAfter = true
            view.startAnimation(fadeOut)
        }
    }
}