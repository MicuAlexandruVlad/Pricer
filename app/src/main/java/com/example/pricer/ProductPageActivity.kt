package com.example.pricer

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pricer.constants.DBLinks
import com.example.pricer.models.Product
import com.example.pricer.models.User
import com.example.pricer.utils.Styles
import com.google.gson.Gson
import okhttp3.internal.wait

class ProductPageActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ProductPageActivity"
    }

    private lateinit var productImage: ImageView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productRating: RatingBar
    private lateinit var favorite: RelativeLayout
    private lateinit var favoriteIv: ImageView
    private lateinit var descriptionHolder: RelativeLayout
    private lateinit var productDescription: TextView
    private lateinit var specsHolder: RelativeLayout

    private lateinit var currentUser: User
    private lateinit var product: Product
    private var isFavorite = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        product = intent.getSerializableExtra("selectedProduct") as Product
        Log.d(TAG, "Product data -> " + Gson().toJson(product))

        productName.text = product.name
        productPrice.text = "$ " + product.price
        if (product.description.isEmpty()) {
            descriptionHolder.visibility = View.GONE
        } else {
            productDescription.text = product.description
        }

        if (product.specTitles.isEmpty()) {
            specsHolder.visibility = View.GONE
        } else {
            specsHolder.visibility = View.VISIBLE
        }

        Glide.with(this)
            .load(DBLinks.productImageLargeUrl(product.id, product.imageId))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    productImage.setImageDrawable(resource!!)
                    window.statusBarColor = Styles.getDominantColor(this@ProductPageActivity, productImage)
                    return true
                }
            }).submit()

        favorite.setOnClickListener {
            if (!isFavorite) {
                favoriteIv.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.heart_filled
                    )
                )
            } else {
                favoriteIv.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.heart_outline
                    )
                )
            }
            isFavorite = !isFavorite
        }
    }

    private fun bindViews() {
        productImage = findViewById(R.id.iv_product_image)
        productName = findViewById(R.id.tv_product_name)
        productPrice = findViewById(R.id.tv_product_price)
        productRating = findViewById(R.id.rb_product_rating)
        favorite = findViewById(R.id.rl_favorite_product)
        favoriteIv = findViewById(R.id.iv_favorite_product)
        descriptionHolder = findViewById(R.id.rl_product_description_holder)
        productDescription = findViewById(R.id.tv_product_description)
        specsHolder = findViewById(R.id.rl_product_specs_holder)
    }
}
