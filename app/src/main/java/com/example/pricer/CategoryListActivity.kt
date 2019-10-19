package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.CategoryAdapter
import com.example.pricer.models.Category
import com.example.pricer.models.Store
import com.example.pricer.models.StoreBrand
import com.example.pricer.models.User

class CategoryListActivity : AppCompatActivity() {

    private lateinit var categoriesRv: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var currentUser: User
    private lateinit var storeBrand: StoreBrand
    private lateinit var selectedStore: Store
    private lateinit var city: String
    private lateinit var country: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        storeBrand = intent.getSerializableExtra("storeBrand") as StoreBrand
        selectedStore = intent.getSerializableExtra("selectedStore") as Store
        city = intent.getStringExtra("storeCity") as String
        country = intent.getStringExtra("storeCountry") as String

        categoryAdapter = CategoryAdapter(Category.initCategories(this), this, currentUser)
        categoriesRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        categoriesRv.isNestedScrollingEnabled = false
        categoriesRv.adapter = categoryAdapter

        categoryAdapter.storeBrand = storeBrand
        categoryAdapter.selectedStore = selectedStore
        categoryAdapter.city = city
        categoryAdapter.country = country

    }

    private fun bindViews() {
        categoriesRv = findViewById(R.id.rv_categories)
    }
}
