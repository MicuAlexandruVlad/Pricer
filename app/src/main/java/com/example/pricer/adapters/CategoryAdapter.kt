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

class CategoryAdapter(private var categories: ArrayList<Category>,
                      private var context: Context,
                      private var currentUser: User) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    companion object {
        const val TAG = "CategoryAdapter"
    }

    var storeBrand = StoreBrand()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.category_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.categoryName.text = category.categoryName
        Glide.with(context).load(category.imageDrawable).into(holder.categoryImage)

        if (!category.isSubCategory) {
            holder.parent.setOnClickListener {
                val intent = Intent(context, SubCategoryListActivity::class.java)
                intent.putExtra("currentUser", currentUser)
                intent.putExtra("categoryName", category.categoryName)
                intent.putExtra("storeBrand", storeBrand)
                (context as Activity).startActivityForResult(intent, RequestCodes.SUB_CATEGORY_LIST_REQ_CODE)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName: TextView = view.findViewById(R.id.tv_category_name)
        val categoryImage: ImageView = view.findViewById(R.id.iv_category)
        val parent: RelativeLayout = view.findViewById(R.id.rl_category_parent)
    }
}