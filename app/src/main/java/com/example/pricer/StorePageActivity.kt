package com.example.pricer

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.ProductAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.events.RegisterEvent
import com.example.pricer.models.Product
import com.example.pricer.models.Store
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.JsonUtils
import com.example.pricer.utils.Styles
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class StorePageActivity : AppCompatActivity() {
    companion object {
        const val TAG = "StorePageActivity"
    }

    private lateinit var recentlyAdded: RecyclerView
    private lateinit var deals: RecyclerView
    private lateinit var featured: RecyclerView

    private lateinit var recentlyAddedProductsAdapter: ProductAdapter
    private lateinit var dealsAdapter: ProductAdapter
    private lateinit var featuredProductsAdapter: ProductAdapter
    private lateinit var recentlyAddedProducts: ArrayList<Product>
    private lateinit var productDeals: ArrayList<Product>
    private lateinit var featuredProducts: ArrayList<Product>
    private lateinit var currentUser: User
    private lateinit var currentStore: Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_page)

        EventBus.getDefault().register(this)
        Styles.storeTheme(this)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        currentStore = intent.getSerializableExtra("selectedStore") as Store

        recentlyAddedProducts = ArrayList()
        productDeals = ArrayList()
        featuredProducts = ArrayList()

        adapterSetup()

        ApiCalls.getFeaturedProducts(this, currentStore.id, 4)
    }

    private fun adapterSetup() {
        recentlyAdded.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
        deals.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
        featured.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)

        recentlyAddedProductsAdapter = ProductAdapter(recentlyAddedProducts, this, currentUser)
        dealsAdapter = ProductAdapter(productDeals, this, currentUser)
        featuredProductsAdapter = ProductAdapter(featuredProducts, this, currentUser)

        recentlyAdded.adapter = recentlyAddedProductsAdapter
        deals.adapter = dealsAdapter
        featured.adapter = featuredProductsAdapter
    }

    private fun bindViews() {
        recentlyAdded = findViewById(R.id.rv_recently_added_products)
        deals = findViewById(R.id.rv_product_deals)
        featured = findViewById(R.id.rv_featured_products)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onProductsReceived(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.objType == ObjectType.OBJ_PRODUCT
            && getResponseEvent.action == Actions.FEATURED_PRODUCTS_RECEIVED) {
            when (getResponseEvent.status) {
                HttpStatus.SC_OK -> {
                    featuredProducts.addAll(JsonUtils.jsonToProductArray(getResponseEvent.jsonResponseArray))

                    runOnUiThread {
                        featuredProductsAdapter.notifyDataSetChanged()
                    }
                }
                else -> {
                    runOnUiThread {
                        Toast.makeText(this, "There was a problem", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
