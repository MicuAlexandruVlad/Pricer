package com.example.pricer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.example.pricer.models.User

class MainActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager
    private lateinit var loading: RelativeLayout

    private lateinit var parentIntent: Intent
    private lateinit var currentUser: User
    private lateinit var adapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        parentIntent = intent
        currentUser = parentIntent.getSerializableExtra("currentUser") as User

        val bundleProducts = Bundle()
        val bundleStores = Bundle()

        initAdapter(bundleProducts, bundleStores, currentUser)
    }

    private fun bindViews() {
        loading = findViewById(R.id.rl_loading)
        pager = findViewById(R.id.pager)
    }

    private fun initAdapter(
        bundleProducts: Bundle,
        bundleStores: Bundle,
        user: User
    ) {
        adapter = MainPagerAdapter(supportFragmentManager, bundleStores, bundleProducts, user)
        pager.adapter = adapter
    }
}
