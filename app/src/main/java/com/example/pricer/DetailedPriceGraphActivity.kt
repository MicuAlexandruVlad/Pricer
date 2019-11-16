package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.PriceChangeAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.ObjectInstanceCreatedEvent
import com.example.pricer.models.PriceChange
import com.example.pricer.models.Product
import com.example.pricer.utils.JsonUtils
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.find

class DetailedPriceGraphActivity : AppCompatActivity() {

    companion object {
        const val TAG = "DetailedPriceGraph"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_price_graph)

        val product = intent.getSerializableExtra("product") as Product

        val priceChangeArray = ArrayList<PriceChange>().also {
            it.addAll(JsonUtils.priceDataToPriceChangeArray(product.historicalPrices, product.priceChangeDates))
        }
        val priceChangeRV: RecyclerView = find(R.id.rv_price_change)
        val adapter = PriceChangeAdapter(priceChangeArray, this)
        priceChangeRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        priceChangeRV.adapter = adapter
        priceChangeRV.isNestedScrollingEnabled = false

        Log.d(TAG, "product -> ${Gson().toJson(product)}")

        val objectInstanceCreatedEvent = ObjectInstanceCreatedEvent()
        objectInstanceCreatedEvent.action = Actions.SEND_PRODUCT_DATA
        objectInstanceCreatedEvent.objectType = ObjectType.OBJ_PRICE_CHANGE_ARRAY
        objectInstanceCreatedEvent.priceChangeArray = priceChangeArray

        EventBus.getDefault().post(objectInstanceCreatedEvent)
    }
}
