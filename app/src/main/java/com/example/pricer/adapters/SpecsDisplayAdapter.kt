package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.*
import com.example.pricer.models.Spec
import com.example.pricer.models.User

class SpecsDisplayAdapter(private var specList: ArrayList<Spec>,
                          private var context: Context,
                          private var currentUser: User
) : RecyclerView.Adapter<SpecsDisplayAdapter.ViewHolder>() {

    companion object {
        const val TAG = "SpecsDisplayAdapter"
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.spec_display_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return specList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = specList[position]

        holder.specTitle.text = item.title
        holder.spec.text = item.spec
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val specTitle: TextView = view.findViewById(R.id.tv_spec_title)
        val spec: TextView = view.findViewById(R.id.tv_spec)
    }
}