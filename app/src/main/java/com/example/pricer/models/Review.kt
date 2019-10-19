package com.example.pricer.models

class Review {
    var id: String = ""
    var storeId: String = ""
    var productId: String = ""

    var rating: Double = -1.0
    var text: String = ""

    var isForStore: Boolean = false
    var isForProduct: Boolean = false

    var addedById: String = ""
    var addedByName: String = ""
}