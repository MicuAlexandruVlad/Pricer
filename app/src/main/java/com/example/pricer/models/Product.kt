package com.example.pricer.models

import java.io.Serializable
import java.math.BigInteger

class Product: Serializable {
    var id: String = ""
    var storeId: String = ""
    var imageId: String = ""

    var name: String = ""
    var description: String = ""
    var price: Double = -1.0
    var manufacturer: String = ""
    var model: String = ""
    var categoryName: String = ""
    var subCategoryName: String = ""
    var rating: Double = -1.0
    var reviewCount = 0
    var historicalPrices: String = ""
    var hasImage: Boolean = false
    var specTitles: String = ""
    var specs: String = ""

    // Popularity indexes
    var clicks: Float = 0F
    var follows: Float = 0F

    var priceChange: Int = 0

    var addedById: String = ""
    var addedByName: String = ""
    var lastEditedById: String = ""
    var lastEditedByName: String = ""

    var productImageSignature: String = ""

}