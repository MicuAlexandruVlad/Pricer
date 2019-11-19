package com.example.pricer.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigInteger

@Entity(tableName = "Products")
class Product: Serializable {

    @PrimaryKey(autoGenerate = true)
    var roomId: Int? = null

    var roomOwner: String = ""

    var id: String = ""
    @Ignore var storeId: String = ""
    @Ignore var imageId: String = ""

    @Ignore var name: String = ""
    @Ignore var description: String = ""
    @Ignore var price: Double = -1.0
    @Ignore var manufacturer: String = ""
    @Ignore var model: String = ""
    @Ignore var categoryName: String = ""
    @Ignore var subCategoryName: String = ""
    @Ignore var rating: Double = -1.0
    @Ignore var reviewCount = 0
    @Ignore var historicalPrices: String = ""
    @Ignore var priceChangeDates: String = ""
    @Ignore var hasImage: Boolean = false
    @Ignore var specTitles: String = ""
    @Ignore var specs: String = ""

    // Popularity indexes
    @Ignore var clicks: Float = 0F
    @Ignore var follows: Float = 0F

    @Ignore var priceChange: Int = 0

    @Ignore var addedById: String = ""
    @Ignore var addedByName: String = ""
    @Ignore var lastEditedById: String = ""
    @Ignore var lastEditedByName: String = ""

    @Ignore var productImageSignature: String = ""

}