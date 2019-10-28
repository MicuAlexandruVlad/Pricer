package com.example.pricer.utils

import android.content.Context
import android.util.TypedValue



class UnitConversion {
    companion object {
        fun dpToPx(context: Context, dp: Int): Int {
            val r = context.resources
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            ).toInt()
        }
    }
}