package com.example.pricer.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import com.example.pricer.R
import com.example.pricer.StoreBrandListActivity
import com.example.pricer.constants.Buttons
import com.example.pricer.constants.RequestCodes
import com.example.pricer.events.ButtonPressedEvent
import com.example.pricer.models.Review
import com.rengwuxian.materialedittext.MaterialEditText
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ReviewFragment: Fragment() {
    companion object {
        const val TAG = "ReviewFragment"
    }

    private lateinit var ratingBar: RatingBar
    private lateinit var reviewText: MaterialEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review, container, false)

        bindViews(view)

        return view
    }

    private fun bindViews(view: View) {
        ratingBar = view.findViewById(R.id.rb_rating)
        reviewText = view.findViewById(R.id.met_review_text)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReviewReceived(buttonPressedEvent: ButtonPressedEvent) {
        if (buttonPressedEvent.buttonId == Buttons.BTN_ADD_PRODUCT
            || buttonPressedEvent.buttonId == Buttons.BTN_POST_REVIEW) {
            val review = Review()
            review.rating = ratingBar.rating.toDouble()
            review.text = reviewText.text.toString()
            postReview(review)
        }
    }

    private fun postReview(review: Review) {
        EventBus.getDefault().post(review)
    }
}