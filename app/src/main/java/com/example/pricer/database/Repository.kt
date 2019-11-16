package com.example.pricer.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.pricer.models.Review

class Repository(context: Context) {
    private val db = RoomDB.getDatabase(context)

    fun getReviewForId(id: String): List<Review> {
        return db.reviewDao().getReviewWithId(id)
    }

    fun getAllReviews(): List<Review> {
        return db.reviewDao().getAllReviews()
    }

    fun insertReview(review: Review) {
        db.reviewDao().insertReview(review)
    }

    fun deleteReview(id: String) {
        db.reviewDao().deleteReview(id)
    }

    fun nukeReviewsTable() {
        db.reviewDao().nukeTable()
    }
}