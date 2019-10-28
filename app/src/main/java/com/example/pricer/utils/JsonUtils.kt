package com.example.pricer.utils

import com.example.pricer.models.Product
import com.example.pricer.models.Store
import com.example.pricer.models.StoreBrand
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

        fun jsonToStoreBrandArray(array: JSONArray): ArrayList<StoreBrand> {
            return ArrayList<StoreBrand>().also {
                for (index in 0 until array.length()) {
                    val obj = array.getJSONObject(index)
                    val storeBrand = StoreBrand()
                    storeBrand.name = obj.getString("_id")
                    storeBrand.initial = storeBrand.name.first().toString()
                    it.add(storeBrand)
                }
            }
        }

        fun jsonToProductArray(array: JSONArray): ArrayList<Product> {
            return ArrayList<Product>().also {
                for (index in 0 until array.length()) {
                    val product = Product()
                    val obj = array.getJSONObject(index)
                    product.id = obj.getString("_id")
                    product.storeId = obj.getString("storeId")
                    product.imageId = obj.getString("imageId")
                    product.name = obj.getString("name")
                    product.description = obj.getString("description")
                    product.price = obj.getDouble("price")
                    product.manufacturer = obj.getString("manufacturer")
                    product.model = obj.getString("model")
                    product.categoryName = obj.getString("categoryName")
                    product.subCategoryName = obj.getString("subCategoryName")
                    product.addedById = obj.getString("addedById")
                    product.addedByName = obj.getString("addedByName")
                    product.lastEditedById = obj.getString("lastEditedById")
                    product.lastEditedByName = obj.getString("lastEditedByName")
                    product.hasImage = obj.getBoolean("hasImage")

                    it.add(product)
                }
            }
        }
    }
}