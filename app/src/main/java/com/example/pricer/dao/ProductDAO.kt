package com.example.pricer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pricer.models.Product

@Dao
interface ProductDAO {
    @Insert
    fun insertProduct(product: Product)

    @Query("Select * From Products")
    fun getAllProducts(): List<Product>

    @Query("Select * From Products Where id = :id")
    fun getProductForId(id: String): Product

    @Query("Delete From Products Where id = :id")
    fun deleteProduct(id: String)

    @Query("Delete From Products")
    fun nukeTable()
}