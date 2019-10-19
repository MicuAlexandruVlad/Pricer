package com.example.pricer.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.pricer.R
import com.example.pricer.StoreBrandListActivity
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsFragment : Fragment() {

    companion object {
        const val TAG = "ProductsFragment"
    }

    private lateinit var changeFragment: ImageView
    private lateinit var addProduct: FloatingActionButton

    private lateinit var bundle: Bundle
    private lateinit var currentUser: User
    private lateinit var parentPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments!!
        currentUser = bundle.getSerializable("currentUser") as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        bindViews(view)

        changeFragment.setOnClickListener {
            parentPager.setCurrentItem(0, true)
        }

        addProduct.setOnClickListener {
            val intent = Intent(context, StoreBrandListActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            startActivityForResult(intent, RequestCodes.ADD_PRODUCT_REQ_CODE)
        }

        return view
    }

    private fun bindViews(view: View) {
        parentPager = activity!!.findViewById(R.id.pager)
        changeFragment = view.findViewById(R.id.iv_back)
        addProduct = view.findViewById(R.id.fab_add_product)
    }
}