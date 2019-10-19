package com.example.pricer.utils

import com.example.pricer.models.Store
import org.json.JSONArray

class JsonUtils {
    companion object {
        fun jsonToStoreArray(array: JSONArray): ArrayList<Store> {
            return ArrayList<Store>().also {
                for (index in 0 until array.length()) {
                    val obj = array.getJSONObject(index)
                    val store = Store()

                    store.id = obj.getString("_id")
                    store.storeImageId = obj.getString("storeImageId")
                    store.storeName = obj.getString("storeName")
                    store.storeCity = obj.getString("storeCity")
                    store.storeCountry = obj.getString("storeCountry")
                    store.storeDescription = obj.getString("storeDescription")
                    store.storePhone = obj.getString("storePhone")
                    store.storeState = obj.getString("storeState")
                    store.hasSchedule = obj.getBoolean("hasSchedule")
                    store.isInUsa = obj.getBoolean("isInUsa")
                    store.storeZip = obj.getString("storeZip")
                    store.storeSchedule = obj.getString("storeSchedule")
                    store.storeStreet = obj.getString("storeStreet")
                    store.hasImage = obj.getBoolean("hasImage")
                    store.originallyAddedById = obj.getString("originallyAddedById")
                    store.originallyAddedByName = obj.getString("originallyAddedByName")
                    store.lastEditedById = obj.getString("lastEditedById")
                    store.lastEditedByName = obj.getString("lastEditedByName")

                    it.add(store)
                }
            }
        }
    }
}