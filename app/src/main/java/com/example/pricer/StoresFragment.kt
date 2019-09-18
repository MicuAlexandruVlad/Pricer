package com.example.pricer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

class StoresFragment : Fragment() {

    companion object {
        const val TAG = "StoresFragment"
    }

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


        return view
    }

    private fun bindViews(view: View) {
        parentPager = activity!!.findViewById(R.id.pager)
    }
}