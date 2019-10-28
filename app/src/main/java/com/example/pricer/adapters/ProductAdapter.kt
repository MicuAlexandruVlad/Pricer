package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pricer.*
import com.example.pricer.constants.DBLinks
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.*

class ProductAdapter(private var products: ArrayList<Product>,
                     private var context: Context,
                     private var currentUser: User
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    companion object {
        const val TAG = "CategoryAdapter"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.product_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        if (product.hasImage) {
            val url = DBLinks.productImageLargeUrl(product.id, product.imageId)
            Glide.with(context).load(url).into(holder.productImage)
        }

        holder.productName.text = product.name
        holder.productPrice.text = "$" + product.price
        holder.productPriceChange.text = "0%"

        holder.productPriceChange.setTextColor(ContextCompat.getColor(context, R.color.md_white))

        holder.body.setOnClickListener {
            val intent = Intent(context, ProductPageActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            intent.putExtra("selectedProduct", product)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productImage: ImageView = view.findViewById(R.id.iv_product_image)
        var productName: TextView = view.findViewById(R.id.tv_product_name)
        var productPrice: TextView = view.findViewById(R.id.tv_product_price)
        var productPriceChange: TextView = view.findViewById(R.id.tv_product_price_change)
        var notRatedYet: TextView = view.findViewById(R.id.tv_not_rated)
        var rating: RatingBar = view.findViewById(R.id.rb_product_rating)
        var body: CardView = view.findViewById(R.id.cv_product_holder)
    }
}