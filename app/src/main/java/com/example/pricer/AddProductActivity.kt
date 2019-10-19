package com.example.pricer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.pricer.constants.Buttons
import com.example.pricer.events.ButtonPressedEvent
import com.example.pricer.models.Product
import com.example.pricer.models.Review
import com.example.pricer.models.Store
import com.example.pricer.models.User
import com.rengwuxian.materialedittext.MaterialEditText
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AddProductActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AddProductActivity"
    }

    private lateinit var name: MaterialEditText
    private lateinit var description: MaterialEditText
    private lateinit var price: MaterialEditText
    private lateinit var manufacturer: MaterialEditText
    private lateinit var model: MaterialEditText
    private lateinit var addImage: RelativeLayout
    private lateinit var productImageCV: CardView
    private lateinit var productImage: ImageView
    private lateinit var writeReview: Switch
    private lateinit var reviewLayout: FrameLayout
    private lateinit var reviewFragment: Fragment

    private lateinit var addProduct: Button

    private lateinit var currentUser: User
    private lateinit var selectedStore: Store
    private lateinit var product: Product
    private lateinit var categoryName: String
    private lateinit var subCategoryName: String
    private var hasImage: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        EventBus.getDefault().register(this)

        bindViews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        categoryName = intent.getStringExtra("categoryName") as String
        subCategoryName = intent.getStringExtra("subCategoryName") as String
        selectedStore = intent.getSerializableExtra("selectedStore") as Store

        writeReview.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                reviewLayout.visibility = View.VISIBLE
            } else {
                reviewLayout.visibility = View.GONE
            }
        }

        addImage.setOnClickListener {
            Log.d(TAG, "Add image clicked")
        }

        addProduct.setOnClickListener {
            product = Product()

            if (name.text.isNullOrEmpty() || description.text.isNullOrEmpty()
                || price.text.isNullOrEmpty()) {
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show()
            } else if (!hasImage) {
                Toast.makeText(this, "Product has no image", Toast.LENGTH_SHORT).show()
            } else {
                EventBus.getDefault().post(ButtonPressedEvent().also {
                    it.buttonId = Buttons.BTN_ADD_PRODUCT
                })
            }
        }
    }

    private fun bindViews() {
        name = findViewById(R.id.met_product_name)
        description = findViewById(R.id.met_product_description)
        price = findViewById(R.id.met_product_current_price)
        manufacturer = findViewById(R.id.met_product_manufacturer)
        model = findViewById(R.id.met_product_model)
        productImageCV = findViewById(R.id.cv_product_image)
        productImage = findViewById(R.id.iv_product_image)
        addImage = findViewById(R.id.rl_add_image)
        addProduct = findViewById(R.id.btn_add_product)
        writeReview = findViewById(R.id.sw_write_review)
        reviewLayout = findViewById(R.id.fl_review)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReviewReceived(review: Review) {
        if (writeReview.isChecked) {
            if (review.rating <= 0.0) {
                Toast.makeText(this, "Product not rated", Toast.LENGTH_SHORT).show()
            } else {
                bindProductData()
                bindReviewData(review)
                // TODO: upload product & review
            }
        } else {
            // TODO: upload product
        }

    }

    private fun bindReviewData(review: Review) {
        review.isForProduct = true
        review.isForStore = false
        review.addedById = currentUser.id
        review.addedByName = currentUser.firstName + " " + currentUser.lastName

        // TODO: update the review.productId on the server side after the product is registered in the DB
    }

    private fun bindProductData() {
        product.storeId = selectedStore.id

        product.name = name.text.toString()
        product.description = description.text.toString()
        product.price = price.text.toString().toDouble()
        product.manufacturer = manufacturer.text.toString()
        product.model = model.text.toString()
        product.categoryName = categoryName
        product.subCategoryName = subCategoryName

        product.addedById = currentUser.id
        product.addedByName = currentUser.firstName + " " + currentUser.lastName
    }
}
