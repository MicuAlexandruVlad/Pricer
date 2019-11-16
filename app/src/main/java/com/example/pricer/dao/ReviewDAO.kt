package com.example.pricer.dao

import androidx.room.*
import com.example.pricer.models.Review

@Dao
interface ReviewDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertReview(review: Review)

    @Query("SELECT * FROM REVIEWS WHERE id = :id")
    fun getReviewWithId(id: String): List<Review>

    @Query("SELECT * from REVIEWS")
    fun getAllReviews(): List<Review>

    @Query("Delete From Reviews Where id = :id")
    fun deleteReview(id: String)

    @Query("Delete From Reviews")
    fun nukeTable()
}