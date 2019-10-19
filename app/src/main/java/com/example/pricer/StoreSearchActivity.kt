package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.StoresResultAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.events.RegisterEvent
import com.example.pricer.models.Store
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User
import com.example.pricer.utils.JsonUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class StoreSearchActivity : AppCompatActivity() {
    companion object {
        const val TAG = "StoreSearchActivity"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var addStore: FloatingActionButton

    private lateinit var adapter: StoresResultAdapter
    private lateinit var foundStores: ArrayList<Store>
    private lateinit var currentUser: User
    private lateinit var storeBrand: StoreBrand
    private lateinit var city: String
    private lateinit var country: String
    private var state: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_search)

        EventBus.getDefault().register(this)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        storeBrand = intent.getSerializableExtra("storeBrand") as StoreBrand
        city = intent.getStringExtra("city") as String
        country = intent.getStringExtra("country") as String

        foundStores = ArrayList()
        adapter = StoresResultAdapter(foundStores, this, currentUser)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        adapter.city = city
        adapter.country = country
        adapter.storeBrand = storeBrand


        ApiCalls.fetchStores(this, storeBrand.name, country, city, state)
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.rv_found_stores)
        addStore = findViewById(R.id.fab_add_store)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.objType == ObjectType.OBJ_STORE
            && getResponseEvent.action == Actions.STORE_SEARCH) {
            when (getResponseEvent.status) {
                HttpStatus.SC_NOT_FOUND ->
                    Toast.makeText(this, "No stores found", Toast.LENGTH_SHORT).show()
                HttpStatus.SC_OK -> {
                    val array = getResponseEvent.jsonResponseArray

                    foundStores.addAll(JsonUtils.jsonToStoreArray(array))
                    Log.d(TAG, "Stores found -> " + foundStores.size)
                    runOnUiThread { adapter.notifyDataSetChanged() }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
