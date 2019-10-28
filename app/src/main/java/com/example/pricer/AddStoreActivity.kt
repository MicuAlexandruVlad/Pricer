package com.example.pricer

import android.app.Activity
import android.content.Intent
import android.graphics.RectF
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pricer.constants.Actions
import com.example.pricer.dialogs.ImageLoaderDialog
import com.example.pricer.events.RegisterEvent
import com.example.pricer.constants.ObjectType
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.Store
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.ImageUtils
import com.rengwuxian.materialedittext.MaterialEditText
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class AddStoreActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AddStoreActivity"
    }

    private lateinit var storeName: MaterialEditText
    private lateinit var storeDescription: MaterialEditText
    private lateinit var storeCountry: MaterialEditText
    private lateinit var storeCity: MaterialEditText
    private lateinit var storeStreet: MaterialEditText
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
    private lateinit var addImage: ImageView
    private lateinit var addStore: Button

    private lateinit var buttonOriginalParams: RelativeLayout.LayoutParams
    private var hasImage: Boolean = false
    private lateinit var currentUser: User
    private var galleryEncodedImageLg: String = ""
    private var galleryEncodedImageSm: String = ""
    private var imageCompressing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_store)

        EventBus.getDefault().register(this)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User

        buttonOriginalParams = addStore.layoutParams as RelativeLayout.LayoutParams
        // placeButton()

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

            // placeButton()
        }

        addImage.setOnClickListener {
            val imageLoaderDialog = ImageLoaderDialog(this)
            imageLoaderDialog.create()
            imageLoaderDialog.show()

            imageLoaderDialog.setOnDismissListener {
                if (!imageLoaderDialog.isCanceled && imageLoaderDialog.isMethodSelected) {
                    when (imageLoaderDialog.galleryClicked) {
                        true -> openGallery()
                        false -> openCamera()
                    }
                }
            }
        }

        addStore.setOnClickListener {
            // checkFieldCompletion()

            val store = Store()
            store.storeName = storeName.text.toString()
            store.storeCity = storeCity.text.toString()
            store.storeCountry = storeCountry.text.toString()
            store.storeStreet = storeStreet.text.toString()
            store.storeDescription = storeDescription.text.toString()
            store.storePhone = storePhone.text.toString()
            store.storeState = storeState.text.toString()
            store.isInUsa = inUsa.isChecked
            store.hasSchedule = storeScheduleKnown.isChecked

            val builder = StringBuilder()
            builder.append(storeScheduleMondayStart.text.toString())
            builder.append("-")
            builder.append(storeScheduleMondayEnd.text.toString())
            builder.append(" ")
            builder.append(storeScheduleTuesdayStart.text.toString())
            builder.append("-")
            builder.append(storeScheduleTuesdayEnd.text.toString())
            builder.append(" ")
            builder.append(storeScheduleWednesdayStart.text.toString())
            builder.append("-")
            builder.append(storeScheduleWednesdayEnd.text.toString())
            builder.append(" ")
            builder.append(storeScheduleThursdayStart.text.toString())
            builder.append("-")
            builder.append(storeScheduleThursdayEnd.text.toString())
            builder.append(" ")
            builder.append(storeScheduleFridayStart.text.toString())
            builder.append("-")
            builder.append(storeScheduleFridayEnd.text.toString())
            builder.append(" ")
            builder.append(storeScheduleSaturdayStart.text.toString())
            builder.append("-")
            builder.append(storeScheduleSaturdayEnd.text.toString())
            builder.append(" ")
            builder.append(storeScheduleSundayStart.text.toString())
            builder.append("-")
            builder.append(storeScheduleSundayEnd.text.toString())

            store.storeSchedule = builder.toString()
            store.hasImage = hasImage
            store.storeZip = storeZip.text.toString()
            store.originallyAddedById = currentUser.id
            store.originallyAddedByName = currentUser.lastName + " " + currentUser.firstName
            store.lastEditedById = currentUser.id
            store.lastEditedByName = currentUser.lastName + " " + currentUser.firstName

            if (imageCompressing) {
                Toast.makeText(
                    this,
                    "Image compression in progress. Please wait.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                ApiCalls.registerStore(this@AddStoreActivity, store, galleryEncodedImageSm, galleryEncodedImageLg)
            }

        }
    }



    private fun checkFieldCompletion() {
        // TODO: I don't think this works
        if (storeName.text!!.isEmpty() || storeCountry.text!!.isEmpty()
            || storeCountry.text!!.isEmpty() || storeCity.text!!.isEmpty()
            || storeZip.text!!.isEmpty()) {
            Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show()
        } else if (!hasImage) {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
        } else if (inUsa.isChecked) {
            if (storeState.text!!.isEmpty()) {
                Toast.makeText(this, "Store state is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openCamera() {

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, RequestCodes.GALLERY_REQ_CODE)
    }

    private fun bindViews() {
        storeName = findViewById(R.id.met_store_name)
        storeDescription = findViewById(R.id.met_store_description)
        storeCountry = findViewById(R.id.met_store_country)
        storeCity = findViewById(R.id.met_store_city)
        storeStreet = findViewById(R.id.met_store_street)
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
        addImage = findViewById(R.id.iv_add_image)
        addStore = findViewById(R.id.btn_add_store)
    }

    private fun placeButton() {
        val buttonRect = computeRectF(addStore)
        val scheduleRect = computeRectF(storeScheduleCBHolder)

        val dist = buttonRect.top - scheduleRect.bottom
        Log.d(TAG, "Distance -> $dist")
        Log.d(TAG, "Button top -> " + buttonRect.top)
        Log.d(TAG, "Layout bottom -> " + scheduleRect.bottom)

        if (dist > 700) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult")
        if (requestCode == RequestCodes.GALLERY_REQ_CODE && resultCode == Activity.RESULT_OK) {
            hasImage = true
            addImage.setPadding(0, 0, 0, 0)
            val uri = data!!.data
            addImage.imageTintList = null

            val bmp = MediaStore.Images.Media.getBitmap(contentResolver, uri)

            Glide.with(this).load(bmp).into(addImage)

            // Log.d(TAG, "File path -> " + file.absolutePath)

            Thread {
                runOnUiThread {
                    Toast.makeText(this, "Image compression in progress...", Toast.LENGTH_SHORT).show()
                }
                imageCompressing = true

                galleryEncodedImageLg = ImageUtils.encodeImage(bmp, false)
                galleryEncodedImageSm = ImageUtils.encodeImage(bmp, true)

                imageCompressing = false
                runOnUiThread {
                    Toast.makeText(this, "Image compression is finished", Toast.LENGTH_SHORT).show()
                }
            }.start()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(registerEvent: RegisterEvent) {
        if (registerEvent.objType == ObjectType.OBJ_STORE
            && registerEvent.action == Actions.STORE_UPLOADED) {
            when (registerEvent.status) {
                HttpStatus.SC_CREATED -> {
                    Toast.makeText(this, "Store has been uploaded", Toast.LENGTH_SHORT).show()
                }

                HttpStatus.SC_CONFLICT -> {
                    Toast.makeText(this, "Store already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        EventBus.getDefault().unregister(this)
    }
}
