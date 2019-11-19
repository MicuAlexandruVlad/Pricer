package com.example.pricer.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.pricer.models.Product
import com.example.pricer.models.Review

class Repository(context: Context) {
    private val db = RoomDB.getDatabase(context)

    fun getReviewForId(id: String): List<Review> {
        return db.reviewDao().getReviewWithId(id)
    }

    fun getAllReviews(): List<Review> {
        return db.reviewDao().getAllReviews()
    }

    fun getProductForId(id: String): Product {
        return db.productDao().getProductForId(id)
    }

    fun getAllProducts(): List<Product> {
        return db.productDao().getAllProducts()
    }

    fun insertReview(review: Review) {
        db.reviewDao().insertReview(review)
    }

    fun insertProduct(product: Product) {
        db.productDao().insertProduct(product)
    }

    fun deleteReview(id: String) {
        db.reviewDao().deleteReview(id)
    }

    fun deleteProduct(id: String) {
        db.productDao().deleteProduct(id)
    }

    fun nukeReviewsTable() {
        db.reviewDao().nukeTable()
    }

    fun nukeProductsTable() {
        db.productDao().nukeTable()
    }
}