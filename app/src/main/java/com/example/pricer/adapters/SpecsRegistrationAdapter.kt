package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.*
import com.example.pricer.models.User
import com.rengwuxian.materialedittext.MaterialEditText

class SpecsRegistrationAdapter(private var blankList: ArrayList<String>,
                               private var context: Context,
                               private var currentUser: User,
                               private var isEdit: Boolean
) : RecyclerView.Adapter<SpecsRegistrationAdapter.ViewHolder>() {

    companion object {
        const val TAG = "SpecsRegistrationAdapter"
    }

    var specTitles: ArrayList<String> = ArrayList()
    var specs: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.specs_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return blankList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = blankList[position]

        if (isEdit) {
            holder.title.setText(specTitles[position])
            holder.spec.setText(specs[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: MaterialEditText = view.findViewById(R.id.met_spec_title)
        val spec: MaterialEditText = view.findViewById(R.id.met_spec)
    }
}