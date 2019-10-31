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
        const val searchStoreGroupStreet: String = baseLink + "stores/search-store-brand-group-street/"
        const val storeBrands: String = baseLink + "stores/search-brands/"
        const val storeSearch: String = baseLink + "stores/store-search/"
        const val registerReview: String = baseLink + "reviews/register-review/"
        const val registerProduct: String = baseLink + "products/register-product/"
        const val uploadProductImage: String = baseLink + "product-images/upload-product-image/"
        const val updateProductPrice: String = baseLink + "products/update-product-price/"
        const val featuredProducts: String = baseLink + "products/featured-products/"
        const val productDeals: String = baseLink + "products/product-deals/"
        const val recentlyAddedProducts: String = baseLink + "products/recently-added-products/"

        fun storeImageLargeUrl(storeId: String, storeImageId: String): String {
            return baseLink + "store-images/store-image-large/$storeId/images/$storeImageId/"
        }

        fun storeImageSmallUrl(storeId: String, storeImageId: String): String {
            return baseLink + "store-images/store-image-small/$storeId/images/$storeImageId/"
        }

        fun productImageLargeUrl(productId: String, productImageId: String): String {
            return baseLink + "product-images/product-image-large/$productId/images/$productImageId/"
        }

        fun productImageSmallUrl(productId: String, productImageId: String): String {
            return baseLink + "product-images/product-image-small/$productId/images/$productImageId/"
        }
    }
}