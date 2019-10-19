package com.example.pricer.events

import org.json.JSONArray
import org.json.JSONObject

class GetResponseEvent {
    var status: Int = -1
    var objType: Int = -1
    var action: Int = -1
    lateinit var jsonResponseObj: JSONObject
    lateinit var jsonResponseArray: JSONArray
}
