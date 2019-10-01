package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pricer.R
import com.example.pricer.StoreBrand

class StoreBrandAdapter(private var storeBrands: ArrayList<StoreBrand>,
                        private var context: Context) : RecyclerView.Adapter<StoreBrandAdapter.ViewHolder>() {

    companion object {
        const val TAG = "StoreBrandAdapter"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.store_brand_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storeBrands.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storeBrand = storeBrands[position]

        holder.storeBrandInitial.text = storeBrand.initial
        holder.storeBrandName.text = storeBrand.name
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val storeBrandName: TextView = view.findViewById(R.id.tv_store_brand_name)
        val storeBrandInitial: TextView = view.findViewById(R.id.tv_store_brand_initial)
    }
}