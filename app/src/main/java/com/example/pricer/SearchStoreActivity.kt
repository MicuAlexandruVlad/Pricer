package com.example.pricer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchStoreActivity : AppCompatActivity() {

    private lateinit var addStore: FloatingActionButton

    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_store)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User

        addStore.setOnClickListener {
            val intent = Intent(this, AddStoreActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            startActivityForResult(intent, RequestCodes.ADD_STORE_REQ_CODE)
        }
    }

    private fun bindViews() {
        addStore = findViewById(R.id.fab_add_store)
    }
}
