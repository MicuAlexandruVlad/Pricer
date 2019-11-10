package com.example.pricer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Reviews")
class Review: Serializable {
    @PrimaryKey(autoGenerate = true)
    var roomId: Int? = null

    var id: String = ""
    var storeId: String = ""
    var productId: String = ""

    var rating: Double = -1.0
    var text: String = ""
    var likes: Int = 0

    var isForStore: Boolean = false
    var isForProduct: Boolean = false

    var addedById: String = ""
    var addedByName: String = ""
    var addedOn: String = ""

    // local variable (not saved in MongoDB)
    // isFavorite - might need to save the id of the liked review in RoomDB
    var isFavorite: Boolean = false
}