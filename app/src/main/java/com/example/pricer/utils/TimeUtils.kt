package com.example.pricer.utils

import android.util.Log
import com.example.pricer.models.PriceChange
import com.example.pricer.models.Review
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.floor

class TimeUtils {
    companion object {
        private const val TAG = "TimeUtils"

        private fun getSystemDate(): Date {
            return Calendar.getInstance().time
        }

        private fun stringToDate(str: String): Date {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

            val dateSubString = str.split("T")[0]
            val timeSubString = str.split("T")[1]

            val year = dateSubString.split("-")[0]
            val month = dateSubString.split("-")[1]
            val day = dateSubString.split("-")[2]

            val hour = timeSubString.split(":")[0]
            val min = timeSubString.split(":")[1]
            val sec = timeSubString.split(":")[2]

            val formattedDate = "$year-$month-$day $hour:$min:$sec"

            return format.parse(formattedDate) as Date

        }

        fun formatReviewTime(array: ArrayList<Review>): ArrayList<Review> {
            val sysDate = getSystemDate()

            for (review in array) {
                val reviewTime = review.addedOn
                val reviewDate = stringToDate(reviewTime)

                Log.d(TAG, "Review date -> $reviewDate")

                review.addedOn = computeTime(sysDate, reviewDate)
            }

            return array
        }

        fun formatPriceChangeTime(array: ArrayList<PriceChange>) {
            val sysDate = getSystemDate()

            for (change in array) {
                val changeTime = change.date
                val changeDate = priceChangeTimeToDate(changeTime)

                change.date = computeTime(sysDate, changeDate)
            }
        }

        private fun priceChangeTimeToDate(date: String): Date {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)


            val timeSubString = date.split(" ")[3]

            val year = date.split(" ")[5]
            val monthName = date.split(" ")[1]
            val day = date.split(" ")[2]

            val hour = timeSubString.split(":")[0]
            val min = timeSubString.split(":")[1]
            val sec = timeSubString.split(":")[2]

            val d = SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monthName)
            val cal = Calendar.getInstance()
            cal.time = d!!

            val month = cal.get(Calendar.MONTH) + 1

            val formattedDate = "$year-$month-$day $hour:$min:$sec"

            return format.parse(formattedDate) as Date
        }

        private fun computeTime(sysDate: Date, date: Date): String {
            val dif = TimeUnit.DAYS.convert(abs(sysDate.time - date.time), TimeUnit.MILLISECONDS).toInt()
            var s = ""
            when {
                dif == 1 -> s = "1 day ago"
                dif in 2..30 -> s = "$dif days ago"
                dif == 0 -> {
                    s = "Today"
                }
                dif / 30 == 1 -> {
                    s = "1 month ago"
                }
                dif / 30 in 2..12 -> {
                    s = "${floor((dif / 30).toDouble())} months ago"
                }
                dif / 365 == 1 -> s = "1 year ago"
                dif / 365 > 1 -> s = "${floor((dif / 365).toDouble())} years ago"
            }

            Log.d(TAG, "compare -> $dif")

            return s
        }


    }


}