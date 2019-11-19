package com.example.pricer.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.pricer.R
import com.example.pricer.adapters.ProductAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.database.Repository
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.models.Product
import com.example.pricer.models.Store
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.JsonUtils
import cz.msebera.android.httpclient.HttpStatus
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.runOnUiThread

class DashboardFragment: Fragment() {
    companion object {
        const val TAG = "DashboardFragment"
    }

    private lateinit var loadingHolder: RelativeLayout
    private lateinit var forward: ImageView
    private lateinit var addedStoresLabel: TextView
    private lateinit var addedProductsLabel: TextView
    private lateinit var favoriteProductsLabel: TextView
    private lateinit var favoriteProductsRV: RecyclerView
    private lateinit var myAddedStoresRV: RecyclerView
    private lateinit var myAddedProductsRV: RecyclerView

    private lateinit var parentPager: ViewPager
    private lateinit var favoriteProducts: ArrayList<Product>
    private lateinit var productsAdapter: ProductAdapter
    private lateinit var myAddedStores: ArrayList<Store>
    private lateinit var myAddedProducts: ArrayList<Product>
    private lateinit var addedProductsAdapter: ProductAdapter
    private lateinit var bundle: Bundle
    private lateinit var currentUser: User
    private lateinit var repository: Repository

    private var canUpdateUi: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)

        Log.d(TAG, "onCreate")

        repository = Repository(context!!)

        bundle = arguments!!
        currentUser = bundle.getSerializable("currentUser") as User

        myAddedStores = ArrayList()
        myAddedProducts = ArrayList()
        favoriteProducts = ArrayList()

        productsAdapter = ProductAdapter(favoriteProducts, context!!, currentUser)
        addedProductsAdapter = ProductAdapter(myAddedProducts, context!!, currentUser)

        getFavoriteProducts()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        bindViews(view)

        favoriteProductsRV.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
        favoriteProductsRV.adapter = productsAdapter

        forward.setOnClickListener {
            parentPager.setCurrentItem(1, true)
        }

        return view

    }

    private fun displayLists() {
        if (myAddedProducts.isEmpty()) {
            addedProductsLabel.visibility = View.GONE
            myAddedProductsRV.visibility = View.GONE
        }

        if (myAddedStores.isEmpty()) {
            addedStoresLabel.visibility = View.GONE
            myAddedStoresRV.visibility = View.GONE
        }

        if (favoriteProducts.isEmpty()) {
            favoriteProductsLabel.visibility = View.GONE
            favoriteProductsRV.visibility = View.GONE
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetResponseEvent(getResponseEvent: GetResponseEvent) {
        if (getResponseEvent.status == HttpStatus.SC_OK) {
            if (getResponseEvent.objType == ObjectType.OBJ_PRODUCT
                && getResponseEvent.action == Actions.FAVORITE_PRODUCTS_RECEIVED) {
                favoriteProducts.addAll(JsonUtils.jsonToProductArray(getResponseEvent.jsonResponseArray))
                runOnUiThread {
                    loadingHolder.visibility = View.GONE
                    productsAdapter.notifyDataSetChanged()
                }
            }
        } else {
            Toast.makeText(context!!, "There was a problem", Toast.LENGTH_SHORT).show()
        }

        runOnUiThread {
            Log.d(TAG,"onGetResponseEvent")
            displayLists()
        }
    }

    private fun bindViews(view: View) {
        parentPager = activity!!.find(R.id.pager)
        loadingHolder = view.find(R.id.rl_loading)
        forward = view.find(R.id.iv_forward)
        favoriteProductsRV = view.find(R.id.rv_favorite_products)
        myAddedProductsRV = view.find(R.id.rv_my_added_products)
        myAddedStoresRV = view.find(R.id.rv_my_added_stores)
        addedProductsLabel = view.find(R.id.tv_my_added_products)
        addedStoresLabel = view.find(R.id.tv_my_added_stores)
        favoriteProductsLabel = view.find(R.id.tv_favorite_products)
    }

    private fun getFavoriteProducts() {
        doAsync {
            val roomProducts = ArrayList<Product>().also { it.addAll(repository.getAllProducts()) }
            val idArray = ArrayList<String>()
            for (product in roomProducts) {
                idArray.add(product.id)
            }

            if (idArray.isNotEmpty()) {
                ApiCalls.getFavoriteProducts(context!!, idArray)
            } else {
                runOnUiThread {
                    displayLists()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (canUpdateUi) {
            displayLists()
        }
        Log.d(TAG,"onResume:")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause:")
        canUpdateUi = true
    }

    override fun onDestroy() {
        super.onDestroy()

        EventBus.getDefault().unregister(this)
        Log.d(TAG, "onDestroy:")
    }
}