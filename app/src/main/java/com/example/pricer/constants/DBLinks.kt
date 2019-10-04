package com.example.pricer.constants

class DBLinks {
    companion object {
        private const val baseLink: String = "http://192.168.0.19:3000/"
        const val registerUserEmail: String = baseLink + "users/register-user-email/"
        const val registerStore: String = baseLink + "stores/register-store/"
        const val uploadStoreImage: String = baseLink + "store-images/upload-store-image/"
        const val searchStoreGroupName: String = baseLink + "stores/search-store-brand-group-name/"
        const val searchStoreGroupCountry: String = baseLink + "stores/search-store-brand-group-country/"
        const val searchStoreGroupCity: String = baseLink + "stores/search-store-brand-group-city/"
        const val searchStoreGroupState: String = baseLink + "stores/search-store-brand-group-state/"
        const val storeBrands: String = baseLink + "stores/search-brands/"
    }
}