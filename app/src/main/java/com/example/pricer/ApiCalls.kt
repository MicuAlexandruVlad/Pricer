package com.example.pricer

import android.content.Context
import android.util.Log
import com.example.pricer.events.RegisterEvent
import com.example.pricer.constants.Actions
import com.example.pricer.constants.DBLinks
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
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
                    }
                    else {
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

        fun registerStore(context: Context, store: Store, storeImageSmData: String, storeImageLgData: String) {
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

                    when (status) {
                        HttpStatus.SC_CREATED -> {
                            // store has been created
                            val id = response.getString("id")

                            val registerEvent = RegisterEvent()
                            registerEvent.status = status
                            registerEvent.id = id
                            registerEvent.objType = ObjectType.OBJ_STORE
                            registerEvent.action = Actions.STORE_UPLOADED
                            emitRegisterEvent(registerEvent)

                            uploadStoreImage(context, storeImageSmData, storeImageLgData, id)
                        }

                        HttpStatus.SC_CONFLICT -> {
                            // store already exists

                            val registerEvent = RegisterEvent()
                            registerEvent.status = status
                            registerEvent.id = ""
                            registerEvent.objType = ObjectType.OBJ_STORE
                            emitRegisterEvent(registerEvent)
                        }
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

        fun uploadStoreImage(context: Context, storeImageSmData: String, storeImageLgData: String, storeId: String) {
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

                    emitGetResponseEvent(getResponseEvent)
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

                    emitGetResponseEvent(getResponseEvent)
                }
            })
        }

        private fun emitRegisterEvent(registerEvent: RegisterEvent) {
            EventBus.getDefault().post(registerEvent)
        }

        private fun emitGetResponseEvent(getResponseEvent: GetResponseEvent) {
            EventBus.getDefault().post(getResponseEvent)
        }
    }
}