package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.*
import com.example.pricer.models.*

class PriceChangeAdapter(private var priceChanges: ArrayList<PriceChange>,
                         private var context: Context
) : RecyclerView.Adapter<PriceChangeAdapter.ViewHolder>() {

    companion object {
        const val TAG = "PriceChangeAdapter"
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.price_change_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return priceChanges.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val priceChange = priceChanges[position]

        holder.currentPrice.text = "$ " + priceChange.price.toString()
        holder.changePercentage.text = "${priceChange.percentage} %"
        holder.priceChangeDate.text = priceChange.date

        when {
            priceChange.percentage == 0.0 -> {
                holder.image.visibility = View.INVISIBLE
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.md_blue_grey_300))
            }

            priceChange.percentage < 0.0 -> {
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.priceDrop))
                holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.arrow_down))
            }

            else -> {
                holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.priceIncrease))
                holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.arrow_up))
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.cv_price_change)
        val image: ImageView = view.findViewById(R.id.iv_price_change)
        val currentPrice: TextView = view.findViewById(R.id.tv_current_price)
        val changePercentage: TextView = view.findViewById(R.id.tv_price_change)
        val priceChangeDate: TextView = view.findViewById(R.id.tv_price_change_date)
    }
}