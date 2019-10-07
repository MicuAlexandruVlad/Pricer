package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.CityAdapter
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CityListActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CityListActivity"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    private lateinit var currentUser: User
    private lateinit var storeBrand: StoreBrand
    private lateinit var storeCountry: String
    private lateinit var cities: ArrayList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        EventBus.getDefault().register(this)

        currentUser = intent.getSerializableExtra("currentUser") as User
        storeBrand = intent.getSerializableExtra("storeBrand") as StoreBrand
        storeCountry = intent.getStringExtra("country") as String

        bindViews()

        cities = ArrayList()

        adapter = CityAdapter(cities, this, currentUser)
        adapter.country = Country().also { it.countryName = storeCountry }
        adapter.storeBrand = storeBrand
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        ApiCalls.searchStoreByCity(this, storeBrand.name, storeCountry)
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.rv_cities)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.objType == ObjectType.OBJ_CITY) {
            when (getResponseEvent.status) {
                HttpStatus.SC_OK -> {
                    for (index in 0 until getResponseEvent.jsonResponseArray.length()) {
                        val obj = getResponseEvent.jsonResponseArray.getJSONObject(index)
                        val city = City()
                        city.name = obj.getString("_id")
                        city.initial = city.name.first().toString()
                        Log.d(TAG, "City -> " + obj.getString("_id"))
                        cities.add(city)

                        runOnUiThread {
                            adapter.notifyItemInserted(index)
                        }
                    }
                }

                HttpStatus.SC_NOT_FOUND -> {

                }
            }
        }
    }
}
