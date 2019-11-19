package com.example.pricer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Users")
class User: Serializable {

    @PrimaryKey(autoGenerate = true)
    var roomId: Int? = null

    var id: String = ""
    var email: String = ""
    var password: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var country: String = ""
    var city: String = ""

    var isGuest: Boolean = false
}