package com.example.pricer.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.pricer.R
import com.rengwuxian.materialedittext.MaterialEditText

class PriceChangeDialog(context: Context) : Dialog(context) {

    private lateinit var cancel: Button
    private lateinit var done: Button
    private lateinit var newPrice: MaterialEditText

    var canceledPressed: Boolean = false
    var donePressed: Boolean = false
    var newValue = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_price_change)

        bindViews()

        cancel.setOnClickListener {
            canceledPressed = true
            donePressed = false
            dismiss()
        }

        done.setOnClickListener {
            donePressed = true
            canceledPressed = false
            if (newPrice.text.isNullOrEmpty()) {
                Toast.makeText(context, "Price field is empty", Toast.LENGTH_SHORT).show()
            } else {
                newValue = newPrice.text.toString().toDouble()
                dismiss()
            }
        }
    }

    private fun bindViews() {
        cancel = findViewById(R.id.btn_cancel)
        done = findViewById(R.id.btn_done)
        newPrice = findViewById(R.id.met_product_price_update)
    }
}