package com.example.pricer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pricer.models.Store

@Dao
interface StoreDAO {

    @Insert
    fun insertStore(store: Store)

    @Query("Select * From Stores where roomOwner = :ownerId")
    fun getAddedStores(ownerId: String): List<Store>
}