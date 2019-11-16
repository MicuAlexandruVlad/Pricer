package com.example.pricer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.adapters.ReviewAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.database.Repository
import com.example.pricer.database.RoomQueries
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.models.Product
import com.example.pricer.models.Review
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.JsonUtils
import com.example.pricer.utils.Styles
import com.example.pricer.utils.TimeUtils
import com.google.gson.Gson
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.doAsync

class ReadReviewsActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ReadReviewsActivity"
    }

    private lateinit var recyclerView: RecyclerView

    private lateinit var currentUser: User
    private lateinit var reviewsAdapter: ReviewAdapter
    private lateinit var reviews: ArrayList<Review>
    private lateinit var product: Product
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_reviews)

        EventBus.getDefault().register(this)
        Styles.storeTheme(this)

        recyclerView = findViewById(R.id.rv_reviews)

        repository = Repository(this)
        listLikedReviews()

        currentUser = intent.getSerializableExtra("currentUser") as User
        product = intent.getSerializableExtra("product") as Product

        reviews = ArrayList()
        reviewsAdapter = ReviewAdapter(reviews, this, currentUser)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = reviewsAdapter

        ApiCalls.getReviewsNoLimit(this, false, product.id)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetResponseEvent(getResponseEvent: GetResponseEvent) {
        when (getResponseEvent.objType) {
            ObjectType.OBJ_REVIEW -> {
                if (getResponseEvent.action == Actions.REVIEWS_RECEIVED_NO_LIMIT
                    && getResponseEvent.status == HttpStatus.SC_OK) {
                    reviews.addAll(TimeUtils.formatReviewTime(JsonUtils
                        .jsonArrayToReviewArray(getResponseEvent.jsonResponseArray)))

                    Log.d(TAG, "Reviews received -> " + reviews.size)

                    runOnUiThread {
                        reviewsAdapter.notifyDataSetChanged()
                    }

                    RoomQueries.setLikedReviews(this, reviews, reviewsAdapter)
                }
            }
        }
    }

    private fun listLikedReviews() {
        doAsync {
            Log.d(TAG, "Interrogating RoomDB")
            val reviews = repository.getAllReviews()
            for (index in reviews.indices) {
                Log.d(TAG, "Liked review with data -> " + Gson().toJson(reviews[index]))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
