package com.example.pricer

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rengwuxian.materialedittext.MaterialEditText
import android.view.inputmethod.EditorInfo
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.events.RegisterEvent
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
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_brand_list)

        EventBus.getDefault().register(this)

        bindViews()

        searchBox.visibility = View.GONE

        searchBox.tag = searchBox.visibility

        search.setOnClickListener {
            searchBox.visibility = View.VISIBLE
            search.visibility = View.GONE
        }

        searchBox.setOnEditorActionListener { view, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.visibility = View.GONE
                search.visibility = View.VISIBLE

                if (searchBox.text.toString().isNotEmpty()) {
                    ApiCalls.searchStoreBrand(this, searchBox.text.toString())
                }
            }
            false
        }
    }

    private fun bindViews() {
        search = findViewById(R.id.iv_search)
        searchBox = findViewById(R.id.met_store_brand_search)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.objType == ObjectType.OBJ_STORE_BRAND) {
            when (getResponseEvent.status) {
                HttpStatus.SC_OK -> {
                    Log.d(TAG, "Received data -> " + getResponseEvent.jsonResponseArray.toString())
                }

                HttpStatus.SC_NOT_FOUND -> {
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
