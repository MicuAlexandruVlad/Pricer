package com.example.pricer

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter (private val fragmentManager: FragmentManager,
                        private val bundleStores: Bundle,
                        private val bundleProducts: Bundle,
                        private val currentUser: User) : FragmentPagerAdapter(fragmentManager)  {

    private val numItems = 2

    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> {
                val storesFragment = StoresFragment()
                bundleStores.putSerializable("currentUser", currentUser)
                storesFragment.arguments = bundleStores
                storesFragment
            }
            else -> {
                val productsFragment = ProductsFragment()
                bundleProducts.putSerializable("currentUser", currentUser)
                productsFragment.arguments = bundleProducts
                productsFragment
            }
        }
    }

    override fun getCount(): Int {
        return numItems
    }
}