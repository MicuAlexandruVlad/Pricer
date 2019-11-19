package com.example.pricer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Stores")
class Store: Serializable {

    @PrimaryKey(autoGenerate = true)
    var roomId: Int? = null

    var roomOwner: String = ""

    var id: String = ""
    var storeImageId: String = ""

    var storeName: String = ""
    var storeDescription: String = ""
    var storeCountry: String = ""
    var storeCity: String = ""
    var storeStreet: String = ""
    var storeState: String = ""
    var storeZip: String = ""
    var storePhone: String = ""
    var storeSchedule: String = ""
    var isInUsa: Boolean = false
    var hasSchedule: Boolean = false
    var hasImage: Boolean = false
    var rating: Double = -1.0

    var originallyAddedByName: String = ""
    var originallyAddedById: String = ""
    var lastEditedByName: String = ""
    var lastEditedById: String = ""
}