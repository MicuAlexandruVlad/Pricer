package com.example.pricer.utils

import android.graphics.Bitmap
import android.util.Log
import com.example.pricer.AddStoreActivity
import com.loopj.android.http.Base64
import org.apache.commons.io.output.ByteArrayOutputStream

class ImageUtils {
    companion object {
        const val TAG = "ImageUtils"

        fun encodeImage(bmp: Bitmap, resize: Boolean): String {
            return Base64.encodeToString(bitmapToByteArray(bmp, resize), Base64.DEFAULT)
        }

        private fun bitmapToByteArray(bmp: Bitmap, resize: Boolean): ByteArray {
            val stream = ByteArrayOutputStream()
            Log.d(TAG, "Original bmp byte count -> " + bmp.byteCount)
            Log.d(TAG, "Original bmp width -> " + bmp.width)
            Log.d(TAG, "Original bmp height -> " + bmp.height)
            if (resize) {
                var bmpSm = Bitmap.createScaledBitmap(bmp, bmp.width / 5,
                    bmp.height / 5, false)
                bmpSm.compress(Bitmap.CompressFormat.JPEG, 60, stream)

                Log.d(TAG, "Resized bmp byte count -> " + bmpSm.byteCount)
                Log.d(TAG, "Resized bmp width -> " + bmpSm.width)
                Log.d(TAG, "Resized bmp height -> " + bmpSm.height)
            } else {
                val streamLg = ByteArrayOutputStream()
                var bmpLg = bmp
                Log.d(TAG, "image byte count before loop -> " + bmpLg.byteCount)
                Log.d(TAG, "image height before loop -> " + bmpLg.height)
                while (bmpLg.width * bmpLg.height > 3686400) {
                    bmpLg = Bitmap.createScaledBitmap(bmpLg, (bmpLg.width / 1.7).toInt(),
                        (bmpLg.height / 1.7).toInt(), false)
                }

                Log.d(TAG, "image byte count after loop -> " + bmpLg.byteCount)
                Log.d(TAG, "image height after loop -> " + bmpLg.height)

                bmpLg.compress(Bitmap.CompressFormat.JPEG, 40, streamLg)

                val byteArray = streamLg.toByteArray()
                Log.d(TAG, "resized byte array size -> " + byteArray.size)
                // resizedBmp.recycle()

                return byteArray
            }
            val byteArray = stream.toByteArray()
            // bmp.recycle()

            return byteArray
        }
    }
}