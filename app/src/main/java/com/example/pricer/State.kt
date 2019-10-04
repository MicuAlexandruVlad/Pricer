package com.example.pricer

import android.graphics.drawable.Drawable
import java.io.Serializable

class State: Serializable {
    var stateName: String = ""
    var stateFlag: Drawable? = null
}