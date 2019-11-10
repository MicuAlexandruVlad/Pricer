package com.example.pricer.database

import android.content.Context
import com.example.pricer.models.Review

class Repository(context: Context) {
    private val db = RoomDB.getDatabase(context)

    fun getReviewForId(id: String): Review {
        return db.reviewDao().getReviewWithId(id)
    }

    fun insertReview(review: Review) {
        db.reviewDao().insertReview(review)
    }

    fun deleteReview(review: Review) {
        db.reviewDao().deleteReview(review)
    }
}