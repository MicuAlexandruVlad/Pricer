package com.example.pricer.utils

import android.content.Context
import android.util.Log
import com.example.pricer.events.RegisterEvent
import com.example.pricer.constants.Actions
import com.example.pricer.constants.DBLinks
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.models.Product
import com.example.pricer.models.Review
import com.example.pricer.models.Store
import com.example.pricer.models.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject

class ApiCalls {
    companion object {
        const val TAG = "ApiCalls"

        fun registerUserEmail(context: Context, user: User) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("email", user.email)
            params.put("password", user.password)
            params.put("firstName", user.firstName)
            params.put("lastName", user.lastName)
            params.put("country", user.country)
            params.put("city", user.city)

            client.post(DBLinks.registerUserEmail, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)
                    val status = response!!.getInt("status")
                    if (status == HttpStatus.SC_CREATED) {
                        val id = response.getString("id")
                        val registerEvent = RegisterEvent()
                        registerEvent.status = status
                        registerEvent.id = id
                        emitRegisterEvent(registerEvent)
                    } else {
                        val registerEvent = RegisterEvent()
                        registerEvent.status = status
                        registerEvent.id = ""
                        registerEvent.objType = ObjectType.OBJ_USER
                        emitRegisterEvent(registerEvent)
                    }
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    super.onFailure(statusCode, headers, throwable, errorResponse)
                    Log.d(TAG, errorResponse.toString())
                }
            })
        }

        fun registerStore(
            context: Context,
            store: Store,
            storeImageSmData: String,
            storeImageLgData: String
        ) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeImageId", store.storeImageId)

            params.put("storeName", store.storeName)
            params.put("storeCity", store.storeCity)
            params.put("storeCountry", store.storeCountry)
            params.put("storeStreet", store.storeStreet)
            params.put("storeDescription", store.storeDescription)
            params.put("storePhone", store.storePhone)
            params.put("storeState", store.storeState)
            params.put("hasSchedule", store.hasSchedule)
            params.put("isInUsa", store.isInUsa)
            params.put("storeZip", store.storeZip)
            params.put("storeSchedule", store.storeSchedule)
            params.put("hasImage", store.hasImage)

            params.put("originallyAddedById", store.originallyAddedById)
            params.put("originallyAddedByName", store.originallyAddedByName)
            params.put("lastEditedById", store.lastEditedById)
            params.put("lastEditedByName", store.lastEditedByName)

            client.post(DBLinks.registerStore, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val status = response!!.getInt("status")

                    Log.d(TAG, "registerStore: Status -> $status")

                    val registerEvent = RegisterEvent()
                    registerEvent.status = status
                    registerEvent.objType = ObjectType.OBJ_STORE

                    when (status) {
                        HttpStatus.SC_CREATED -> {
                            // store has been created
                            val id = response.getString("id")

                            registerEvent.id = id
                            registerEvent.action = Actions.STORE_UPLOADED


                            uploadStoreImage(
                                context,
                                storeImageSmData,
                                storeImageLgData,
                                id
                            )
                        }

                        HttpStatus.SC_CONFLICT -> {
                            // store already exists
                            registerEvent.id = ""
                        }
                    }

                    emitRegisterEvent(registerEvent)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    super.onFailure(statusCode, headers, throwable, errorResponse)
                    Log.d(TAG, errorResponse.toString())
                }
            })
        }

        fun uploadStoreImage(
            context: Context,
            storeImageSmData: String,
            storeImageLgData: String,
            storeId: String
        ) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeId", storeId)
            params.put("storeImageSmData", storeImageSmData)
            params.put("storeImageLgData", storeImageLgData)

            client.post(DBLinks.uploadStoreImage, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val status = response!!.getInt("status")

                    val registerEvent = RegisterEvent()
                    registerEvent.status = status
                    if (status == HttpStatus.SC_CREATED) {
                        registerEvent.id = response.getString("id")
                    } else {
                        registerEvent.id = ""
                    }
                    registerEvent.action = Actions.STORE_IMAGE_UPLOADED
                    emitRegisterEvent(registerEvent)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    super.onFailure(statusCode, headers, throwable, errorResponse)
                }
            })
        }

        fun searchStoreBrand(context: Context, brandName: String) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeBrandKeyword", brandName)

            client.get(DBLinks.searchStoreGroupName, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val status = response!!.getInt("status")
                    val res = response.getJSONArray("result")

                    val getResponseEvent = GetResponseEvent()
                    getResponseEvent.objType = ObjectType.OBJ_STORE_BRAND
                    getResponseEvent.status = status
                    getResponseEvent.jsonResponseArray = res

                    emitGetResponseEvent(
                        getResponseEvent
                    )
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    super.onFailure(statusCode, headers, throwable, errorResponse)
                }
            })
        }

        fun searchStoreByCountry(context: Context, brandName: String) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeBrandKeyword", brandName)

            client.get(DBLinks.searchStoreGroupCountry, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val status = response!!.getInt("status")
                    val res = response.getJSONArray("result")

                    Log.d(TAG, "searchStoreByCountry: response -> $response")

                    val getResponseEvent = GetResponseEvent()
                    getResponseEvent.objType = ObjectType.OBJ_COUNTRY
                    getResponseEvent.status = status
                    getResponseEvent.jsonResponseArray = res

                    emitGetResponseEvent(
                        getResponseEvent
                    )
                }
            })
        }

        fun searchStoreByCity(context: Context, brandName: String, countryName: String) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeBrandKeyword", brandName)
            params.put("storeCountry", countryName)

            client.get(DBLinks.searchStoreGroupCity, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val status = response!!.getInt("status")
                    val res = response.getJSONArray("result")

                    Log.d(TAG, "searchStoreByCity: response -> $response")

                    val getResponseEvent = GetResponseEvent()
                    getResponseEvent.objType = ObjectType.OBJ_CITY
                    getResponseEvent.status = status
                    getResponseEvent.jsonResponseArray = res

                    emitGetResponseEvent(
                        getResponseEvent
                    )
                }
            })
        }

        fun fetchStores(
            context: Context,
            storeName: String,
            countryName: String,
            cityName: String,
            stateName: String
        ) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeName", storeName)
            params.put("storeCountry", countryName)
            params.put("storeCity", cityName)
            params.put("storeState", stateName)

            client.get(DBLinks.storeSearch, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val status = response!!.getInt("status")

                    val getResponseEvent = GetResponseEvent()
                    getResponseEvent.status = status
                    getResponseEvent.objType = ObjectType.OBJ_STORE
                    getResponseEvent.action = Actions.STORE_SEARCH

                    when (status) {
                        HttpStatus.SC_OK -> {
                            getResponseEvent.jsonResponseArray = response.getJSONArray("result")
                        }
                    }

                    emitGetResponseEvent(
                        getResponseEvent
                    )
                }
            })

        }

        fun uploadReview(context: Context, review: Review) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeId", review.storeId)
            params.put("productId", review.productId)
            params.put("rating", review.rating)
            params.put("text", review.text)
            params.put("isForStore", review.isForStore)
            params.put("isForProduct", review.isForProduct)
            params.put("addedById", review.addedById)
            params.put("addedByName", review.addedByName)

            client.post(DBLinks.registerReview, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val registerEvent = RegisterEvent()
                    registerEvent.action = Actions.REVIEW_UPLOADED
                    registerEvent.status = response!!.getInt("status")
                    registerEvent.objType = ObjectType.OBJ_REVIEW

                    emitRegisterEvent(registerEvent)
                }
            })
        }

        fun uploadProduct(
            context: Context,
            product: Product,
            encodedImageSm: String,
            encodedImageLg: String,
            hasReview: Boolean,
            review: Review?
        ) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeId", product.storeId)
            params.put("imageId", product.imageId)
            params.put("name", product.name)
            params.put("description", product.description)
            params.put("price", product.price)
            params.put("manufacturer", product.manufacturer)
            params.put("model", product.model)
            params.put("categoryName", product.categoryName)
            params.put("subCategoryName", product.subCategoryName)
            params.put("addedById", product.addedById)
            params.put("addedByName", product.addedByName)
            params.put("lastEditedById", product.lastEditedById)
            params.put("lastEditedByName", product.lastEditedByName)
            params.put("hasImage", product.hasImage)
            params.put("addedById", product.addedById)
            params.put("addedByName", product.addedByName)
            params.put("lastEditedById", product.lastEditedById)
            params.put("lastEditedByName", product.lastEditedByName)

            client.post(DBLinks.registerProduct, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val registerEvent = RegisterEvent()
                    registerEvent.action = Actions.PRODUCT_UPLOADED
                    registerEvent.status = response!!.getInt("status")
                    registerEvent.objType = ObjectType.OBJ_PRODUCT
                    registerEvent.id = response.getString("id")

                    Log.d(TAG, "Product saved with id -> " + registerEvent.id)

                    emitRegisterEvent(registerEvent)

                    if (hasReview && registerEvent.status == HttpStatus.SC_CREATED && review != null) {
                        review.productId = registerEvent.id
                        uploadReview(context, review)
                    }

                    if (product.hasImage) {
                        uploadProductImage(context, encodedImageSm, encodedImageLg, registerEvent.id)
                    }
                }
            })
        }

        private fun uploadProductImage(
            context: Context,
            encodedImageSm: String,
            encodedImageLg: String,
            productId: String
        ) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("productId", productId)
            params.put("encodedImageSm", encodedImageSm)
            params.put("encodedImageLg", encodedImageLg)

            client.post(DBLinks.uploadProductImage, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val status = response!!.getInt("status")

                    Log.d(TAG, "Image upload response -> $response")

                    val registerEvent = RegisterEvent()
                    registerEvent.status = status
                    registerEvent.id = response.getString("id")
                    registerEvent.action = Actions.PRODUCT_IMAGE_UPLOADED
                    registerEvent.objType = ObjectType.OBJ_PRODUCT_IMAGE
                    emitRegisterEvent(registerEvent)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    throwable: Throwable?,
                    errorResponse: JSONObject?
                ) {
                    super.onFailure(statusCode, headers, throwable, errorResponse)

                    Log.d(TAG, "Image upload fail response -> $errorResponse")
                }
            })
        }

        fun getFeaturedProducts(context: Context, storeId: String, limit: Int) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeId", storeId)
            params.put("limit", limit)

            client.get(DBLinks.featuredProducts, params, object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONObject?
                ) {
                    super.onSuccess(statusCode, headers, response)

                    val getResponseEvent = GetResponseEvent()
                    getResponseEvent.status = response!!.getInt("status")
                    getResponseEvent.jsonResponseArray = response.getJSONArray("result")
                    getResponseEvent.objType = ObjectType.OBJ_PRODUCT
                    getResponseEvent.action = Actions.FEATURED_PRODUCTS_RECEIVED

                    emitGetResponseEvent(getResponseEvent)
                }
            })
        }

        fun getProductDeals(context: Context, storeId: String, limit: Int) {

        }


        fun getRecentlyAddedProducts(context: Context, storeId: String, limit: Int) {

        }


        private fun emitRegisterEvent(registerEvent: RegisterEvent) {
            EventBus.getDefault().post(registerEvent)
        }

        private fun emitGetResponseEvent(getResponseEvent: GetResponseEvent) {
            EventBus.getDefault().post(getResponseEvent)
        }
    }
}