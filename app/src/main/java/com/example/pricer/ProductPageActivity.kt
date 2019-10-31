package com.example.pricer

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pricer.adapters.SpecsDisplayAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.DBLinks
import com.example.pricer.constants.ObjectType
import com.example.pricer.dialogs.PriceChangeDialog
import com.example.pricer.events.UpdateEvent
import com.example.pricer.models.Product
import com.example.pricer.models.Spec
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.Styles
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ProductPageActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ProductPageActivity"
    }

    private lateinit var productImage: ImageView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var priceHolder: CardView
    private lateinit var productRating: RatingBar
    private lateinit var favorite: RelativeLayout
    private lateinit var favoriteIv: ImageView
    private lateinit var descriptionHolder: RelativeLayout
    private lateinit var productDescription: TextView
    private lateinit var specsHolder: RelativeLayout
    private lateinit var specsRv: RecyclerView
    private lateinit var edit: FloatingActionButton

    private lateinit var currentUser: User
    private lateinit var product: Product
    private var isFavorite = false
    private lateinit var specList: ArrayList<Spec>
    private lateinit var adapter: SpecsDisplayAdapter
    private var index: Int = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        EventBus.getDefault().register(this)

        bindViews()

        specList = ArrayList()

        currentUser = intent.getSerializableExtra("currentUser") as User
        product = intent.getSerializableExtra("selectedProduct") as Product
        index = intent.getIntExtra("index", -1)
        populateSpecList(product.specTitles, product.specs)

        adapter = SpecsDisplayAdapter(specList, this, currentUser)
        specsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        specsRv.adapter = adapter

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
                    window.statusBarColor = Styles.getColorFromImage(this@ProductPageActivity, productImage)
                    return true
                }
            }).submit()

        priceHolder.setOnClickListener {
            val priceChangeDialog = PriceChangeDialog(this)
            priceChangeDialog.create()
            priceChangeDialog.show()

            priceChangeDialog.setOnDismissListener {
                if (priceChangeDialog.donePressed) {
                    // Update product price
                    product.historicalPrices += priceChangeDialog.newValue.toString() + "!_!"
                    product.price = priceChangeDialog.newValue
                    productPrice.text = "$ " + priceChangeDialog.newValue

                    ApiCalls.updateProductPrice(this, product)
                }
            }
        }

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

        edit.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            intent.putExtra("isEdit", true)
            intent.putExtra("selectedProduct", product)
            intent.putExtra("currentUser", currentUser)
            startActivity(intent)
        }
    }

    private fun populateSpecList(specTitles: String, specs: String) {
        val size = specTitles.split("!_!").size
        val t = specTitles.split("!_!")
        val s = specs.split("!_!")
        for (index in 0 until size) {
            val spec = Spec()
            spec.title = t[index]
            spec.spec = s[index]

            specList.add(spec)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUpdateEvent(updateEvent: UpdateEvent) {
        if (updateEvent.objType == ObjectType.OBJ_PRODUCT && updateEvent.action == Actions.PRODUCT_PRICE_UPDATED) {
            if (updateEvent.status == HttpStatus.SC_OK) {
                runOnUiThread {
                    Toast.makeText(this, "Price updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this, "There was a problem. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindViews() {
        productImage = findViewById(R.id.iv_product_image)
        productName = findViewById(R.id.tv_product_name)
        productPrice = findViewById(R.id.tv_product_price)
        priceHolder = findViewById(R.id.cv_product_price)
        productRating = findViewById(R.id.rb_product_rating)
        favorite = findViewById(R.id.rl_favorite_product)
        favoriteIv = findViewById(R.id.iv_favorite_product)
        descriptionHolder = findViewById(R.id.rl_product_description_holder)
        productDescription = findViewById(R.id.tv_product_description)
        specsHolder = findViewById(R.id.rl_product_specs_holder)
        specsRv = findViewById(R.id.rv_product_specs)
        edit = findViewById(R.id.fab_edit_product)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onBackPressed() {
        intent.putExtra("product", product)
        intent.putExtra("index", index)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
