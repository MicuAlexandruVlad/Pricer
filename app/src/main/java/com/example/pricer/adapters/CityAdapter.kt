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
import com.example.pricer.models.City
import com.example.pricer.models.Country
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User

class CityAdapter(private var cities: ArrayList<City>,
                  private var context: Context,
                  private var currentUser: User
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    companion object {
        const val TAG = "CityAdapter"
    }

    var storeBrand: StoreBrand? = null
    var country: Country? = null
    var isProductAdded = false

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.city_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]

        holder.cityInitial.text = city.initial
        holder.cityName.text = city.name
        holder.parent.setOnClickListener {
            val intent = Intent(context, StoreSearchActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            intent.putExtra("city", city.name)
            intent.putExtra("country", country!!.countryName)
            intent.putExtra("storeBrand", storeBrand)
            intent.putExtra("isProductAdded", isProductAdded)
            (context as Activity).startActivityForResult(intent, RequestCodes.FOUND_STORES_REQ_CODE)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.tv_city_name)
        val cityInitial: TextView = view.findViewById(R.id.tv_city_initial)
        val parent: RelativeLayout = view.findViewById(R.id.rl_city_parent)
    }
}