package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.CategoryAdapter
import java.util.*

class CategoryListActivity : AppCompatActivity() {

    private lateinit var categoriesRv: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var currentUser: User
    private lateinit var storeBrand: StoreBrand

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        storeBrand = intent.getSerializableExtra("storeBrand") as StoreBrand

        categoryAdapter = CategoryAdapter(Category.initCategories(this), this, currentUser)
        categoriesRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        categoriesRv.isNestedScrollingEnabled = false
        categoriesRv.adapter = categoryAdapter

        categoryAdapter.storeBrand = storeBrand
    }

    private fun bindViews() {
        categoriesRv = findViewById(R.id.rv_categories)
    }
}
