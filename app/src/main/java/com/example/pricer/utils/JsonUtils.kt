package com.example.pricer.utils

import com.example.pricer.models.Product
import com.example.pricer.models.Review
import com.example.pricer.models.Store
import com.example.pricer.models.StoreBrand
import com.loopj.android.http.RequestParams
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
                    product.rating = obj.getDouble("rating")
                    product.reviewCount = obj.getInt("reviewCount")
                    product.historicalPrices = obj.getString("historicalPrices")
                    product.specTitles = obj.getString("specTitles")
                    product.specs = obj.getString("specs")
                    product.clicks = obj.getLong("clicks").toFloat()
                    product.follows = obj.getLong("follows").toFloat()
                    product.addedById = obj.getString("addedById")
                    product.addedByName = obj.getString("addedByName")
                    product.lastEditedById = obj.getString("lastEditedById")
                    product.lastEditedByName = obj.getString("lastEditedByName")
                    product.hasImage = obj.getBoolean("hasImage")
                    product.productImageSignature = obj.getString("productImageSignature")

                    it.add(product)
                }
            }
        }

        fun productToRequestParams(product: Product): RequestParams {
            return RequestParams().also {
                it.put("id", product.id)
                it.put("storeId", product.storeId)
                it.put("imageId", product.imageId)
                it.put("name", product.name)
                it.put("description", product.description)
                it.put("price", product.price)
                it.put("manufacturer", product.manufacturer)
                it.put("model", product.model)
                it.put("categoryName", product.categoryName)
                it.put("subCategoryName", product.subCategoryName)
                it.put("rating", product.rating)
                it.put("reviewCount", product.reviewCount)
                it.put("historicalPrices", product.historicalPrices)
                it.put("hasImage", product.hasImage)
                it.put("specTitles", product.specTitles)
                it.put("specs", product.specs)
                it.put("clicks", product.clicks)
                it.put("follows", product.follows)
                it.put("addedById", product.addedById)
                it.put("addedByName", product.addedByName)
                it.put("lastEditedById", product.lastEditedById)
                it.put("lastEditedByName", product.lastEditedByName)
                it.put("productImageSignature", product.productImageSignature)
            }
        }

        fun jsonArrayToReviewArray(array: JSONArray): ArrayList<Review> {
            return ArrayList<Review>().also {
                for (index in 0 until array.length()) {
                    val obj = array.getJSONObject(index)
                    val review = Review()

                    review.id = obj.getString("_id")
                    review.storeId = obj.getString("storeId")
                    review.productId = obj.getString("productId")
                    review.rating = obj.getDouble("rating")
                    review.text = obj.getString("text")
                    review.likes = obj.getInt("likes")
                    review.isForProduct = obj.getBoolean("isForProduct")
                    review.isForStore = obj.getBoolean("isForStore")
                    review.addedById = obj.getString("addedById")
                    review.addedByName = obj.getString("addedByName")
                    review.addedOn = obj.getString("createdAt")

                    it.add(review)
                }
            }
        }
    }
}