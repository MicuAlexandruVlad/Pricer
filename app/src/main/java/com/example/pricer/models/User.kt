package com.example.pricer.models

import java.io.Serializable

class User: Serializable {
    var id: String = ""
    var email: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var country: String = ""
    var city: String = ""

    var isGuest: Boolean = false
}