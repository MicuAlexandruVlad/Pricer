package com.example.pricer

import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RelativeLayout
import com.rengwuxian.materialedittext.MaterialEditText
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_store.*


class AddStoreActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AddStoreActivity"
    }

    private lateinit var storeName: MaterialEditText
    private lateinit var storeDescription: MaterialEditText
    private lateinit var storeCountry: MaterialEditText
    private lateinit var storeCity: MaterialEditText
    private lateinit var inUsa: CheckBox
    private lateinit var storeState: MaterialEditText
    private lateinit var storeZip: MaterialEditText
    private lateinit var storePhone: MaterialEditText
    private lateinit var storeScheduleKnown: CheckBox
    private lateinit var storeSchedule: RelativeLayout
    private lateinit var storeScheduleCBHolder: RelativeLayout
    private lateinit var storeScheduleMondayStart: MaterialEditText
    private lateinit var storeScheduleMondayEnd: MaterialEditText
    private lateinit var storeScheduleTuesdayStart: MaterialEditText
    private lateinit var storeScheduleTuesdayEnd: MaterialEditText
    private lateinit var storeScheduleWednesdayStart: MaterialEditText
    private lateinit var storeScheduleWednesdayEnd: MaterialEditText
    private lateinit var storeScheduleThursdayStart: MaterialEditText
    private lateinit var storeScheduleThursdayEnd: MaterialEditText
    private lateinit var storeScheduleFridayStart: MaterialEditText
    private lateinit var storeScheduleFridayEnd: MaterialEditText
    private lateinit var storeScheduleSaturdayStart: MaterialEditText
    private lateinit var storeScheduleSaturdayEnd: MaterialEditText
    private lateinit var storeScheduleSundayStart: MaterialEditText
    private lateinit var storeScheduleSundayEnd: MaterialEditText
    private lateinit var addStore: Button

    private lateinit var buttonOriginalParams: RelativeLayout.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_store)

        bindViews()

        buttonOriginalParams = addStore.layoutParams as RelativeLayout.LayoutParams
        //placeButton()

        storeState.visibility = View.GONE
        storeSchedule.visibility = View.GONE

        inUsa.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                storeState.visibility = View.VISIBLE
            } else {
                storeState.visibility = View.GONE
            }
        }

        storeScheduleKnown.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                storeSchedule.visibility = View.VISIBLE
            } else {
                storeSchedule.visibility = View.GONE
            }

            placeButton()
        }


    }

    private fun bindViews() {
        storeName = findViewById(R.id.met_store_name)
        storeDescription = findViewById(R.id.met_store_description)
        storeCountry = findViewById(R.id.met_store_country)
        storeCity = findViewById(R.id.met_store_city)
        inUsa = findViewById(R.id.cb_in_us)
        storeState = findViewById(R.id.met_store_state)
        storeZip = findViewById(R.id.met_store_zip)
        storePhone = findViewById(R.id.met_store_phone_number)
        storeScheduleKnown = findViewById(R.id.cb_schedule)
        storeSchedule = findViewById(R.id.rl_schedule)
        storeScheduleCBHolder = findViewById(R.id.rl_store_schedule)
        storeScheduleMondayStart = findViewById(R.id.met_monday_start)
        storeScheduleMondayEnd = findViewById(R.id.met_monday_end)
        storeScheduleTuesdayStart = findViewById(R.id.met_tuesday_start)
        storeScheduleTuesdayEnd = findViewById(R.id.met_tuesday_end)
        storeScheduleWednesdayStart = findViewById(R.id.met_wednesday_start)
        storeScheduleWednesdayEnd = findViewById(R.id.met_wednesday_end)
        storeScheduleThursdayStart = findViewById(R.id.met_thursday_start)
        storeScheduleThursdayEnd = findViewById(R.id.met_thursday_end)
        storeScheduleFridayStart = findViewById(R.id.met_friday_start)
        storeScheduleFridayEnd = findViewById(R.id.met_friday_end)
        storeScheduleSaturdayStart = findViewById(R.id.met_saturday_start)
        storeScheduleSaturdayEnd = findViewById(R.id.met_saturday_end)
        storeScheduleSundayStart = findViewById(R.id.met_sunday_start)
        storeScheduleSundayEnd = findViewById(R.id.met_sunday_end)
        addStore = findViewById(R.id.btn_add_store)
    }

    private fun placeButton() {
        val buttonRect = computeRectF(addStore)
        val scheduleRect = computeRectF(storeScheduleCBHolder)

        val dist = buttonRect.top - scheduleRect.bottom
        Log.d(TAG, "Distance -> $dist")
        Log.d(TAG, "Button top -> " + buttonRect.top)
        Log.d(TAG, "Layout bottom -> " + scheduleRect.bottom)

        if (dist > 200) {
            val params = addStore.layoutParams as RelativeLayout.LayoutParams
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            params.removeRule(RelativeLayout.BELOW)
            addStore.layoutParams = params
        } else {
            val params = addStore.layoutParams as RelativeLayout.LayoutParams
            params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            params.addRule(RelativeLayout.BELOW, R.id.rl_schedule)
            addStore.layoutParams = buttonOriginalParams
        }
    }

    private fun computeRectF(view: View): RectF {
        val location = IntArray(2)
        view.getLocationInWindow(location)
        return RectF(location[0].toFloat(),
            location[1].toFloat(), (location[0] + view.measuredWidth).toFloat(), (location[1] + view.measuredHeight).toFloat()
        )
    }
}
