package com.example.pricer

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.StoreBrandAdapter
import com.example.pricer.constants.ObjectType
import com.example.pricer.constants.RequestCodes
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.JsonUtils
import com.example.pricer.utils.Styles
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rengwuxian.materialedittext.MaterialEditText
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SearchStoreActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SearchStoreActivity"
    }

    private lateinit var addStore: FloatingActionButton
    private lateinit var storeName: MaterialEditText
    private lateinit var searchStore: LinearLayout
    private lateinit var message: TextView
    private lateinit var recyclerView: RecyclerView

    private lateinit var currentUser: User
    private lateinit var storeBrandArray: ArrayList<StoreBrand>
    private lateinit var adapter: StoreBrandAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_store)

        EventBus.getDefault().register(this)

        Styles.storeTheme(this)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        storeBrandArray = ArrayList()
        adapter = StoreBrandAdapter(storeBrandArray, this, currentUser, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        searchStore.visibility = View.GONE


        if (currentUser.isGuest) {
            addStore.visibility = View.GONE
        }

        addStore.setOnClickListener {
            val intent = Intent(this, AddStoreActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            startActivityForResult(intent, RequestCodes.ADD_STORE_REQ_CODE)
        }

        storeName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 is CharSequence) {
                    if (p0.isNotEmpty() && p0.length > 2) {
                        searchStore.visibility = View.VISIBLE
                    } else {
                        searchStore.visibility = View.GONE
                    }
                }
            }
        })

        searchStore.setOnClickListener {
            val storeText = storeName.text.toString()
            when {
                storeText.isNullOrEmpty() -> Toast.makeText(this, "Store name can not be empty", Toast.LENGTH_SHORT).show()
                storeText.length < 3 -> Toast.makeText(this, "You must type at lest 3 letters", Toast.LENGTH_SHORT).show()
                else -> {
                    Log.d(TAG, "Searching for store")
                    ApiCalls.searchStoreBrand(this, storeText)
                }
            }
        }
    }

    private fun bindViews() {
        addStore = findViewById(R.id.fab_add_store)
        storeName = findViewById(R.id.met_store_search)
        searchStore = findViewById(R.id.ll_search_store)
        message = findViewById(R.id.tv_message)
        recyclerView = findViewById(R.id.rv_store_brands)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onStoreResponse(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.objType == ObjectType.OBJ_STORE_BRAND && getResponseEvent.status == HttpStatus.SC_OK) {
            val array = getResponseEvent.jsonResponseArray
            storeBrandArray.clear()
            storeBrandArray.addAll(JsonUtils.jsonToStoreBrandArray(array))

            runOnUiThread {
                adapter.notifyDataSetChanged()
                if (storeBrandArray.isNotEmpty()) {
                    message.visibility = View.GONE
                }
            }
        }
    }
}
