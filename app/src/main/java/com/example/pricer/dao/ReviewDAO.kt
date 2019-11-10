package com.example.pricer.dao

import androidx.room.*
import com.example.pricer.models.Review

@Dao
interface ReviewDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertReview(review: Review)

    @Delete
    fun deleteReview(review: Review)

    @Query("SELECT * FROM REVIEWS WHERE id = :id")
    fun getReviewWithId(id: String): Review
}