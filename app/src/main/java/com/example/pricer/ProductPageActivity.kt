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
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.example.pricer.adapters.ReviewAdapter
import com.example.pricer.adapters.SpecsDisplayAdapter
import com.example.pricer.constants.*
import com.example.pricer.database.Repository
import com.example.pricer.dialogs.PriceChangeDialog
import com.example.pricer.events.*
import com.example.pricer.models.Product
import com.example.pricer.models.Review
import com.example.pricer.models.Spec
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.JsonUtils
import com.example.pricer.utils.Styles
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
    private lateinit var menu: FloatingActionButton
    private lateinit var menuHolder: LinearLayout
    private lateinit var edit: FloatingActionButton
    private lateinit var review: FloatingActionButton
    private lateinit var reviewRv: RecyclerView
    private lateinit var numReviews: TextView

    private lateinit var currentUser: User
    private lateinit var product: Product
    private var isFavorite = false
    private var isMenuOpen = false
    private lateinit var specList: ArrayList<Spec>
    private lateinit var specsDisplayAdapter: SpecsDisplayAdapter
    private lateinit var reviewArray: ArrayList<Review>
    private lateinit var reviewAdapter: ReviewAdapter
    private var index: Int = -1
    private lateinit var repository: Repository

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        EventBus.getDefault().register(this)

        bindViews()

        specList = ArrayList()
        reviewArray = ArrayList()

        repository = Repository(this)

        currentUser = intent.getSerializableExtra("currentUser") as User
        product = intent.getSerializableExtra("selectedProduct") as Product
        index = intent.getIntExtra("index", -1)
        populateSpecList(product.specTitles, product.specs)

        specsDisplayAdapter = SpecsDisplayAdapter(specList, this, currentUser)
        specsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        specsRv.adapter = specsDisplayAdapter

        reviewAdapter = ReviewAdapter(reviewArray, this, currentUser)
        reviewRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        reviewRv.adapter = reviewAdapter

        initViews(true)

        ApiCalls.getReviews(this, 5, false, product.id)

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

        menu.setOnClickListener {
            val closeToMenu = AnimatedVectorDrawableCompat.create(this, R.drawable.close_to_menu)
            val menuToClose = AnimatedVectorDrawableCompat.create(this, R.drawable.menu_to_close)
            menu.setImageDrawable(menuToClose)
            if (isMenuOpen) {
                menuHolder.visibility = View.GONE
                menu.setImageDrawable(closeToMenu)
                closeToMenu!!.start()
            } else {
                menuHolder.visibility = View.VISIBLE
                menu.setImageDrawable(menuToClose)
                menuToClose!!.start()
            }

            isMenuOpen = !isMenuOpen
        }

        edit.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            intent.putExtra("isEdit", true)
            intent.putExtra("selectedProduct", product)
            intent.putExtra("currentUser", currentUser)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        review.setOnClickListener {
            val intent = Intent(this, WriteReviewActivity::class.java)
            startActivityForResult(intent, RequestCodes.WRITE_REVIEW_REQ_CODE)
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

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetResponseEvent(getResponseEvent: GetResponseEvent) {
        when (getResponseEvent.objType) {
            ObjectType.OBJ_REVIEW -> {
                when (getResponseEvent.action) {
                    Actions.REVIEWS_RECEIVED -> {
                        if (getResponseEvent.status == HttpStatus.SC_OK) {
                            val array = getResponseEvent.jsonResponseArray

                            reviewArray.addAll(JsonUtils.jsonArrayToReviewArray(array))
                            reviewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onProductDataChanged(objectInstanceCreatedEvent: ObjectInstanceCreatedEvent) {
        // Product data has been changed by the user - this comes from AddProductActivity
        if (objectInstanceCreatedEvent.objectType == ObjectType.OBJ_PRODUCT
            && objectInstanceCreatedEvent.action == Actions.PRODUCT_DATA_UPDATED) {
            product = objectInstanceCreatedEvent.product!!
            initViews(objectInstanceCreatedEvent.imageChanged)

            specList.clear()
            populateSpecList(product.specTitles, product.specs)
            specsDisplayAdapter.notifyDataSetChanged()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onButtonPressedEvent(buttonPressedEvent: ButtonPressedEvent) {
        when (buttonPressedEvent.objType) {
            ObjectType.OBJ_REVIEW -> {
                when (buttonPressedEvent.buttonId) {
                    Buttons.BTN_LIKE_REVIEW -> {
                        Log.d(TAG, "Liked review at index -> " + buttonPressedEvent.index)

                        val likedReview = reviewArray[buttonPressedEvent.index]

                        Thread {
                            repository.insertReview(likedReview)
                        }.start()
                        // send liked review to socket

                        ApiCalls.postLikedReviewToSocket(this, likedReview)
                    }

                    Buttons.BTN_DISLIKE_REVIEW -> {
                        Log.d(TAG, "Disliked review at index -> " + buttonPressedEvent.index)

                        val dislikedReview = reviewArray[buttonPressedEvent.index]

                        Thread {
                            repository.deleteReview(dislikedReview)
                        }.start()

                        ApiCalls.postDislikedReviewToSocket(this, dislikedReview)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(loadImage: Boolean) {
        productName.text = product.name
        productPrice.text = "$ " + product.price
        numReviews.text = "(" + product.reviewCount + ")"
        productRating.rating = product.rating.toFloat()
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

        if (loadImage) {
            Glide.with(this)
                .load(DBLinks.productImageLargeUrl(product.id, product.imageId))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                .signature(ObjectKey(product.productImageSignature))
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
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RequestCodes.WRITE_REVIEW_REQ_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                // upload review

                val review = data.getSerializableExtra("review") as Review
                review.productId = product.id
                review.isForStore = false
                review.isForProduct = true
                review.addedById = currentUser.id
                review.likes = 0
                review.addedByName = currentUser.firstName + " " + currentUser.lastName

                ApiCalls.uploadReview(this, review, false)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(registerEvent: RegisterEvent) {
        if (registerEvent.objType == ObjectType.OBJ_REVIEW
            && registerEvent.action == Actions.REVIEW_UPLOADED) {
            when (registerEvent.status) {
                HttpStatus.SC_CREATED -> {
                    Toast.makeText(this, "Review uploaded", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "There was a problem", Toast.LENGTH_SHORT).show()
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
        menu = findViewById(R.id.fab_menu)
        menuHolder = findViewById(R.id.ll_menu_holder)
        edit = findViewById(R.id.fab_edit_product)
        review = findViewById(R.id.fab_review_product)
        reviewRv = findViewById(R.id.rv_product_reviews)
        numReviews = findViewById(R.id.tv_num_reviews)
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
