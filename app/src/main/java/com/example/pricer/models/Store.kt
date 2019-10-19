package com.example.pricer.models

import java.io.Serializable

class Store: Serializable {
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