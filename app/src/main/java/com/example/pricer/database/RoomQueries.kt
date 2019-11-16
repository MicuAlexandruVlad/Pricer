package com.example.pricer.database

import android.content.Context
import com.example.pricer.adapters.ReviewAdapter
import com.example.pricer.models.Review
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread

class RoomQueries {
    companion object {
        fun setLikedReviews(
            context: Context,
            reviews: ArrayList<Review>,
            reviewsAdapter: ReviewAdapter
        ) {
            val repository = Repository(context)
            for (index in reviews.indices) {
                doAsync {
                    if (repository.getReviewForId(reviews[index].id).isNotEmpty()) {
                        reviews[index].isFavorite = true
                        context.runOnUiThread {
                            reviewsAdapter.notifyItemChanged(index)
                        }
                    }
                }
            }
        }
    }
}