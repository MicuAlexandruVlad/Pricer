package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rengwuxian.materialedittext.MaterialEditText
import android.view.inputmethod.EditorInfo
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pricer.adapters.StoreBrandAdapter
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class StoreBrandListActivity : AppCompatActivity() {

    companion object {
        const val TAG = "StoreBrandListActivity"
    }

    private lateinit var search: ImageView
    private lateinit var searchBox: MaterialEditText
    private lateinit var recyclerViewInitial: RecyclerView
    private lateinit var recyclerViewSearch: RecyclerView

    private lateinit var storeBrandsInitial: ArrayList<StoreBrand>
    private lateinit var storeBrandsSearch: ArrayList<StoreBrand>
    private lateinit var storeBrandInitialAdapter: StoreBrandAdapter
    private lateinit var storeBrandSearchAdapter: StoreBrandAdapter
    private lateinit var currentUser: User
    private var searchedKeyword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_brand_list)

        EventBus.getDefault().register(this)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User

        storeBrandsInitial = ArrayList()
        storeBrandsSearch = ArrayList()

        searchBox.visibility = View.GONE
        recyclerViewInitial.visibility = View.VISIBLE
        recyclerViewSearch.visibility = View.GONE

        storeBrandInitialAdapter = StoreBrandAdapter(storeBrandsInitial, this, currentUser)
        storeBrandSearchAdapter = StoreBrandAdapter(storeBrandsSearch, this, currentUser)
        recyclerViewSearch.adapter = storeBrandSearchAdapter
        recyclerViewInitial.adapter = storeBrandInitialAdapter
        recyclerViewSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewSearch.isNestedScrollingEnabled = false
        recyclerViewInitial.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewInitial.isNestedScrollingEnabled = false

        searchBox.tag = searchBox.visibility

        search.setOnClickListener {
            searchBox.visibility = View.VISIBLE
            search.visibility = View.GONE
        }

        searchBox.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (searchBox.text.toString().length < 3) {
                    Toast.makeText(this, "Please enter at least 3 characters to search", Toast.LENGTH_SHORT).show()
                } else {
                    view.visibility = View.GONE
                    search.visibility = View.VISIBLE

                    if (searchBox.text.toString().isNotEmpty()) {
                        if (searchBox.text.toString().compareTo(searchedKeyword, true) != 0) {
                            ApiCalls.searchStoreBrand(this, searchBox.text.toString())
                        }
                    } else {
                        recyclerViewSearch.visibility = View.GONE
                        recyclerViewInitial.visibility = View.VISIBLE
                    }

                    searchedKeyword = searchBox.text.toString()
                }
            }
            false
        }
    }

    private fun bindViews() {
        search = findViewById(R.id.iv_search)
        searchBox = findViewById(R.id.met_store_brand_search)
        recyclerViewInitial = findViewById(R.id.rv_store_brand_initial)
        recyclerViewSearch = findViewById(R.id.rv_store_brand_search)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.objType == ObjectType.OBJ_STORE_BRAND) {
            when (getResponseEvent.status) {
                HttpStatus.SC_OK -> {
                    storeBrandsSearch.clear()
                    Log.d(TAG, "Received data -> " + getResponseEvent.jsonResponseArray.toString())
                    runOnUiThread {
                        storeBrandSearchAdapter.notifyDataSetChanged()
                        recyclerViewInitial.visibility = View.GONE
                        recyclerViewSearch.visibility = View.VISIBLE
                    }
                    for (index in 0 until getResponseEvent.jsonResponseArray.length()) {
                        val obj = getResponseEvent.jsonResponseArray.getJSONObject(index)
                        val storeBrand = StoreBrand()
                        storeBrand.name = obj.getString("_id")
                        storeBrand.initial = storeBrand.name.first().toString()
                        storeBrandsSearch.add(storeBrand)

                        runOnUiThread {
                            storeBrandSearchAdapter.notifyItemInserted(index)
                        }
                    }
                }

                HttpStatus.SC_NOT_FOUND -> {
                    storeBrandsSearch.clear()
                    runOnUiThread {
                        storeBrandSearchAdapter.notifyDataSetChanged()
                    }
                    Toast.makeText(this, "Nothing found :(", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
