package com.example.pricer

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pricer.constants.Actions
import com.example.pricer.constants.Buttons
import com.example.pricer.constants.ObjectType
import com.example.pricer.constants.RequestCodes
import com.example.pricer.dialogs.ImageLoaderDialog
import com.example.pricer.events.ButtonPressedEvent
import com.example.pricer.events.ObjectInstanceCreatedEvent
import com.example.pricer.events.RegisterEvent
import com.example.pricer.models.Product
import com.example.pricer.models.Review
import com.example.pricer.models.Store
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.ImageUtils
import com.rengwuxian.materialedittext.MaterialEditText
import cz.msebera.android.httpclient.HttpStatus
import kotlinx.android.synthetic.main.activity_add_product.*
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
    private var hasImage: Boolean = false
    private lateinit var encodedImageSm: String
    private lateinit var encodedImageLg: String
    private var reviewUploaded = false
    private var productUploaded = false
    private var imageUploaded = false

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

        productImageCV.visibility = View.GONE

        addImage.setOnClickListener {
            val imageLoaderDialog = ImageLoaderDialog(this)
            imageLoaderDialog.create()
            imageLoaderDialog.show()

            imageLoaderDialog.setOnDismissListener {
                if (imageLoaderDialog.isMethodSelected) {
                    if (imageLoaderDialog.galleryClicked) {
                        launchGalleryIntent()
                    }
                    if (imageLoaderDialog.cameraClicked) {
                        launchCameraIntent()
                    }
                }
            }
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

    private fun launchCameraIntent() {

    }

    private fun launchGalleryIntent() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, RequestCodes.GALLERY_REQ_CODE)
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
                Log.d(TAG, "Upload all")
                // TODO: upload product, review & image
                ApiCalls.uploadProduct(this, product, encodedImageSm, encodedImageLg, true, review)
            }
        } else {
            // TODO: upload product & image
            Log.d(TAG, "Upload just the product & image")
            ApiCalls.uploadProduct(this, product, encodedImageSm, encodedImageLg, false, null)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(registerEvent: RegisterEvent) {
        if (registerEvent.status == HttpStatus.SC_CREATED) {
            when (registerEvent.objType) {
                ObjectType.OBJ_PRODUCT -> {
                    productUploaded = true
                }

                ObjectType.OBJ_REVIEW -> {
                    reviewUploaded = true
                }

                ObjectType.OBJ_PRODUCT_IMAGE -> {
                    imageUploaded = true
                }
            }
            if (productUploaded && reviewUploaded && imageUploaded) {
                // TODO: finish the activity and send the result back to the other activities
                Log.d(TAG, "Product, review and image uploaded")
                showProductUploadedDialog()
            }
        } else {
            Toast.makeText(this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProductUploadedDialog() {
        val builder = AlertDialog.Builder(this)
            .setTitle("Product Uploaded")
            .setMessage(getString(R.string.product_uploaded))
            .setNeutralButton("OK") { dialogInterface, _ ->
                // notifies any subscribed activity that the user has added a new product
                emitObjectInstanceCreatedEvent()
                dialogInterface.dismiss()
                finish()
            }

        builder.create()
        builder.show()
    }

    private fun emitObjectInstanceCreatedEvent() {
        val objectInstanceCreatedEvent = ObjectInstanceCreatedEvent()
        objectInstanceCreatedEvent.action = Actions.PRODUCT_CREATED
        objectInstanceCreatedEvent.objectType = ObjectType.OBJ_PRODUCT
        objectInstanceCreatedEvent.product = product

        EventBus.getDefault().post(objectInstanceCreatedEvent)
    }

    private fun bindReviewData(review: Review) {
        review.isForProduct = true
        review.isForStore = false
        review.addedById = currentUser.id
        review.addedByName = currentUser.firstName + " " + currentUser.lastName
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
        product.hasImage = hasImage

        product.addedById = currentUser.id
        product.addedByName = currentUser.firstName + " " + currentUser.lastName
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RequestCodes.GALLERY_REQ_CODE -> {
                    if (data != null) {
                        hasImage = true

                        productImageCV.visibility = View.VISIBLE

                        val uri = data.data
                        val bmp = MediaStore.Images.Media.getBitmap(contentResolver, uri)

                        Glide.with(this).load(bmp).into(productImage)

                        runOnUiThread {
                            tv_add_image.text = "Change Image"
                            tv_add_image.isAllCaps = true

                            iv_add_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.reload))
                        }

                        encodedImageSm = ImageUtils.encodeImage(bmp, true)
                        encodedImageLg = ImageUtils.encodeImage(bmp, false)
                    }
                }
            }
        }
    }
}
