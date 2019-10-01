package com.example.pricer.dialogs

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.pricer.R

class StoreRegistrationDialog(context: Context) : Dialog(context) {

    private lateinit var openCameraCV: CardView
    private lateinit var openCamera: ImageView
    private lateinit var openGalleryCV: CardView
    private lateinit var openGallery: ImageView
    private lateinit var cancel: Button
    private lateinit var cont: Button

    var cameraClicked: Boolean = false
    var galleryClicked: Boolean = false
    var isCanceled: Boolean = false
    var isMethodSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_store_registration)

        bindViews()

        cancel.setOnClickListener {
            isCanceled = true
            dismiss()
        }

        cont.setOnClickListener {
            if (!isMethodSelected) {
                Toast.makeText(context, "No method selected", Toast.LENGTH_SHORT).show()
            } else {
                dismiss()
            }
        }

        openGalleryCV.setOnClickListener {
            if (!galleryClicked) {
                animateCardElevation(openGalleryCV, 0f, 12f)
            }
            if (cameraClicked) {
                animateCardElevation(openCameraCV, 12f, 0f)
            }
            galleryClicked = true
            cameraClicked = false
            isMethodSelected = true
        }

        openCameraCV.setOnClickListener {
            if (!cameraClicked) {
                animateCardElevation(openCameraCV, 0f, 12f)
            }
            if (galleryClicked) {
                animateCardElevation(openGalleryCV, 12f, 0f)
            }
            cameraClicked = true
            galleryClicked = false
            isMethodSelected = true
        }
    }

    private fun bindViews() {
        openCameraCV = findViewById(R.id.cv_method_camera)
        openCamera = findViewById(R.id.iv_method_camera)
        openGalleryCV = findViewById(R.id.cv_method_gallery)
        openGallery = findViewById(R.id.iv_method_gallery)
        cancel = findViewById(R.id.btn_cancel)
        cont = findViewById(R.id.btn_continue)
    }

    private fun animateCardElevation(card: CardView, start: Float, end: Float) {
        val animator = ObjectAnimator.ofFloat(card, "cardElevation", start, end)
        animator.start()
    }
}