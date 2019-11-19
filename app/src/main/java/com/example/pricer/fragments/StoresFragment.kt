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
import com.example.pricer.SearchStoreActivity
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.find

class StoresFragment : Fragment() {

    companion object {
        const val TAG = "StoresFragment"
    }

    private lateinit var forward: ImageView
    private lateinit var back: ImageView
    private lateinit var searchStore: FloatingActionButton

    private lateinit var bundle: Bundle
    private lateinit var currentUser: User
    private lateinit var parentPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments!!
        currentUser = bundle.getSerializable("currentUser") as User
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stores, container, false)

        bindViews(view)

        forward.setOnClickListener {
            parentPager.setCurrentItem(2, true)
        }

        back.setOnClickListener {
            parentPager.setCurrentItem(0, true)
        }

        searchStore.setOnClickListener {
            val intent = Intent(context, SearchStoreActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            startActivityForResult(intent, RequestCodes.SEARCH_STORE_REQ_CODE)
        }

        return view
    }

    private fun bindViews(view: View) {
        parentPager = activity!!.findViewById(R.id.pager)
        forward = view.findViewById(R.id.iv_forward)
        back = view.findViewById(R.id.iv_backward)
        searchStore = view.findViewById(R.id.fab_search_store)
    }
}