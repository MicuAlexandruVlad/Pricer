package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pricer.*
import com.example.pricer.constants.DBLinks
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.Store
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User

class StoresResultAdapter(private var stores: ArrayList<Store>,
                          private var context: Context,
                          private var currentUser: User
) : RecyclerView.Adapter<StoresResultAdapter.ViewHolder>() {

    companion object {
        const val TAG = "StoresResultAdapter"
    }

    var storeBrand = StoreBrand()
    var city = ""
    var country = ""
    var isProductAdded = false

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.store_result_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = stores[position]

        holder.storeName.text = store.storeName
        holder.storeAddress.text = /*store.storeStreet + ", " + */store.storeCity + ", " + store.storeZip +
                ", " + store.storeCountry

        if (store.rating == -1.0) {
            holder.notRated.visibility = View.VISIBLE
            holder.storeRating.visibility = View.GONE
        } else {
            holder.notRated.visibility = View.GONE
            holder.storeRating.visibility = View.VISIBLE

            holder.storeRating.stepSize = 0.1F
            holder.storeRating.rating = store.rating.toFloat()
        }

        Log.d(TAG, "Store id -> " + store.id)
        Log.d(TAG, "Store image id -> " + store.storeImageId)

        if (store.hasImage && store.storeImageId.isNotEmpty()) {
            val url = DBLinks.storeImageLargeUrl(store.id, store.storeImageId)
            Glide.with(context).load(url).into(holder.storeImage)
            Log.d(TAG, "Store image url -> $url")
        }

        if (isProductAdded) {
            holder.body.setOnClickListener {
                val intent = Intent(context, CategoryListActivity::class.java)
                intent.putExtra("currentUser", currentUser)
                intent.putExtra("storeCity", city)
                intent.putExtra("storeCountry", country)
                intent.putExtra("selectedStore", store)
                intent.putExtra("storeBrand", storeBrand)
                (context as Activity).startActivityForResult(intent, RequestCodes.CATEGORY_LIST_REQ_CODE)
            }
        } else {
            holder.body.setOnClickListener {
                val intent = Intent(context, StorePageActivity::class.java)
                intent.putExtra("currentUser", currentUser)
                intent.putExtra("storeCity", city)
                intent.putExtra("storeCountry", country)
                intent.putExtra("selectedStore", store)
                intent.putExtra("storeBrand", storeBrand)
                (context as Activity).startActivityForResult(intent, RequestCodes.STORE_PAGE_REQ_CODE)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val body: CardView = view.findViewById(R.id.cv_store_item_body)
        val storeImage: ImageView = view.findViewById(R.id.iv_store_item_image)
        val storeName: TextView = view.findViewById(R.id.tv_store_item_name)
        val storeRating: RatingBar = view.findViewById(R.id.rb_store_item_rating)
        val storeAddress: TextView = view.findViewById(R.id.tv_store_item_address)
        val notRated: TextView = view.findViewById(R.id.tv_store_item_no_rating)
    }
}