package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.*
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User

class StoreBrandAdapter(private var storeBrands: ArrayList<StoreBrand>,
                        private var context: Context,
                        private var currentUser: User,
                        private var isProductAdded: Boolean
) : RecyclerView.Adapter<StoreBrandAdapter.ViewHolder>() {

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storeBrand = storeBrands[position]

        holder.storeBrandInitial.text = storeBrand.initial
        holder.storeBrandName.text = storeBrand.name
        holder.parent.setOnClickListener {
            val intent = Intent(context, CountryListActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            intent.putExtra("storeBrand", storeBrand)
            intent.putExtra("isProductAdded", isProductAdded)
            (context as Activity).startActivityForResult(intent, RequestCodes.COUNTRY_LIST_REQ_CODE)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val storeBrandName: TextView = view.findViewById(R.id.tv_store_brand_name)
        val storeBrandInitial: TextView = view.findViewById(R.id.tv_store_brand_initial)
        val parent: RelativeLayout = view.findViewById(R.id.rl_store_brand_parent)
    }
}