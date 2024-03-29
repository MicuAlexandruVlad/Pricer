package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.CountryAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.events.ObjectInstanceCreatedEvent
import com.example.pricer.models.Country
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CountryListActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CountryListActivity"
    }

    private lateinit var rv: RecyclerView

    private lateinit var adapter: CountryAdapter
    private lateinit var currentUser: User
    private lateinit var storeBrand: StoreBrand
    private lateinit var countries: ArrayList<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        EventBus.getDefault().register(this)

        currentUser = intent.getSerializableExtra("currentUser") as User
        storeBrand = intent.getSerializableExtra("storeBrand") as StoreBrand
        val isProductAdded = intent.getBooleanExtra("isProductAdded", false)

        bindViews()

        countries = ArrayList()

        adapter = CountryAdapter(countries, this, currentUser)
        adapter.isProductAdded = isProductAdded
        adapter.storeBrand = storeBrand
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.isNestedScrollingEnabled = false
        rv.adapter = adapter

        ApiCalls.searchStoreByCountry(this, storeBrand.name)


    }

    private fun bindViews() {
        rv = findViewById(R.id.rv_countries)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.objType == ObjectType.OBJ_COUNTRY) {
            when (getResponseEvent.status) {
                HttpStatus.SC_OK -> {
                    val array = ArrayList<String>()
                    for (index in 0 until getResponseEvent.jsonResponseArray.length()) {
                        val obj = getResponseEvent.jsonResponseArray.getJSONObject(index)
                        array.add(obj.getString("_id"))
                        Log.d(TAG, "Country -> " + obj.getString("_id"))
                    }
                    countries.addAll(Country.getCountriesForList(this, array))
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }

                HttpStatus.SC_NOT_FOUND -> {

                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onProductCreated(objectInstanceCreatedEvent: ObjectInstanceCreatedEvent) {
        if (objectInstanceCreatedEvent.objectType == ObjectType.OBJ_PRODUCT
            && objectInstanceCreatedEvent.action == Actions.PRODUCT_CREATED) {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}