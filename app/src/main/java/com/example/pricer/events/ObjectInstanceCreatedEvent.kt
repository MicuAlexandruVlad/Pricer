package com.example.pricer.events

import com.example.pricer.models.Product

class ObjectInstanceCreatedEvent {
    var objectType: Int = -1
    var action: Int = -1

    var product: Product? = null
}