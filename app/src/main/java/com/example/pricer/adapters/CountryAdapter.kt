package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pricer.*
import com.example.pricer.constants.RequestCodes

class CountryAdapter(private var countries: ArrayList<Country>,
                     private var context: Context,
                     private var currentUser: User) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    companion object {
        const val TAG = "CategoryAdapter"
    }

    var storeBrand = StoreBrand()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.country_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]

        holder.countryName.text = country.countryName
        Glide.with(context).load(country.countryFlag).into(holder.flag)

        holder.parent.setOnClickListener {
            val intent = Intent(context, SubCategoryListActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            intent.putExtra("country", country.countryName)
            intent.putExtra("storeBrand", storeBrand)
            (context as Activity).startActivityForResult(intent, RequestCodes.SUB_CATEGORY_LIST_REQ_CODE)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName: TextView = view.findViewById(R.id.tv_country_name)
        val flag: ImageView = view.findViewById(R.id.iv_flag)
        val parent: RelativeLayout = view.findViewById(R.id.rl_country_parent)
    }
}