package com.example.pricer.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.*
import com.example.pricer.constants.Buttons
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.ButtonPressedEvent
import com.example.pricer.models.Review
import com.example.pricer.models.User
import com.rengwuxian.materialedittext.MaterialEditText
import org.greenrobot.eventbus.EventBus

class ReviewAdapter(private var reviewList: ArrayList<Review>,
                    private var context: Context,
                    private var currentUser: User
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    companion object {
        const val TAG = "ReviewAdapter"
    }

    var specTitles: ArrayList<String> = ArrayList()
    var specs: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.review_list_item, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewList[position]

        holder.fullName.text = review.addedByName
        holder.time.text = review.addedOn
        holder.rating.rating = review.rating.toFloat()
        holder.ratingValue.text = review.rating.toString()
        holder.reviewText.text = review.text
        holder.likes.text = review.likes.toString()
        holder.firstLetter.text = review.addedByName.first().toString()

        if (review.text.isEmpty()) {
            holder.reviewText.visibility = View.GONE
        }

        holder.like.setOnClickListener {
            review.isFavorite = !review.isFavorite
            val buttonPressedEvent = ButtonPressedEvent()
            buttonPressedEvent.index = position
            buttonPressedEvent.objType = ObjectType.OBJ_REVIEW

            if (review.isFavorite) {
                review.likes++
                holder.likes.text = review.likes.toString()
                buttonPressedEvent.buttonId = Buttons.BTN_LIKE_REVIEW
            } else {
                review.likes--
                holder.likes.text = review.likes.toString()
                buttonPressedEvent.buttonId = Buttons.BTN_DISLIKE_REVIEW
            }

            Log.d(TAG, "Item at index: $position is favorite -> " + review.isFavorite)

            changeLikeDrawable(holder, review.isFavorite)
            emitButtonPressedEvent(buttonPressedEvent)
        }
    }

    private fun changeLikeDrawable(holder: ViewHolder, isFavorite: Boolean) {
        if (isFavorite) {
            //filled heart
            holder.like.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.heart_filled))
        } else {
            //outline heart
            holder.like.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.heart_outline))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstLetterHolder: CardView = view.findViewById(R.id.cv_user_first_letter)
        val firstLetter: TextView = view.findViewById(R.id.tv_user_first_letter)
        val fullName: TextView = view.findViewById(R.id.tv_user_name)
        val time: TextView = view.findViewById(R.id.tv_review_time)
        val rating: RatingBar = view.findViewById(R.id.rb_rating)
        val ratingValue: TextView = view.findViewById(R.id.tv_rating)
        val reviewText: TextView = view.findViewById(R.id.tv_review_text)
        val likes: TextView = view.findViewById(R.id.tv_review_likes)
        val like: ImageView = view.findViewById(R.id.iv_review_favorite)
    }

    private fun emitButtonPressedEvent(buttonPressedEvent: ButtonPressedEvent) {
        EventBus.getDefault().post(buttonPressedEvent)
    }
}