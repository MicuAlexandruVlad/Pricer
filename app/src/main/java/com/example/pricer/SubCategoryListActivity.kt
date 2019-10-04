package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.CategoryAdapter

class SubCategoryListActivity : AppCompatActivity() {

    private lateinit var subCategoryRv: RecyclerView
    private lateinit var subCategoryAdapter: CategoryAdapter

    private lateinit var currentUser: User
    private lateinit var storeBrand: StoreBrand
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        storeBrand = intent.getSerializableExtra("storeBrand") as StoreBrand
        category = intent.getStringExtra("categoryName") as String

        subCategoryAdapter = CategoryAdapter(Category.initSubCategories(this, category),
            this, currentUser)
        subCategoryRv.adapter = subCategoryAdapter
        subCategoryRv.layoutManager = LinearLayoutManager(this
            , LinearLayoutManager.VERTICAL, false)
        subCategoryRv.isNestedScrollingEnabled = false
    }

    private fun bindViews() {
        subCategoryRv = findViewById(R.id.rv_sub_categories)
    }
}
