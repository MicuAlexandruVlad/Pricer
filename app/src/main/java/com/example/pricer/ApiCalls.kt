package com.example.pricer

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.pricer.events.RegisterEvent
import com.example.pricer.types.ObjectType
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
                        EventBus.getDefault().post(registerEvent)
                    }
                    else {
                        val registerEvent = RegisterEvent()
                        registerEvent.status = status
                        registerEvent.id = ""
                        registerEvent.objType = ObjectType.OBJ_USER
                        EventBus.getDefault().post(registerEvent)
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

        fun registerStore(context: Context, store: Store, storeImageData: String) {
            val client = AsyncHttpClient()
            val params = RequestParams()

            params.put("storeImageId", store.storeImageId)

            params.put("storeName", store.storeName)
            params.put("storeCity", store.storeCity)
            params.put("storeCountry", store.storeCountry)
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
    }
}