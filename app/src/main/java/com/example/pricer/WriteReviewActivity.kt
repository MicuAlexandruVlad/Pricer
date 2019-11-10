package com.example.pricer

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pricer.constants.Buttons
import com.example.pricer.events.ButtonPressedEvent
import com.example.pricer.models.Review
import kotlinx.android.synthetic.main.activity_write_review.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class WriteReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        EventBus.getDefault().register(this)

        btn_post_review.setOnClickListener {
            val buttonPressedEvent = ButtonPressedEvent()
            buttonPressedEvent.buttonId = Buttons.BTN_POST_REVIEW

            EventBus.getDefault().post(buttonPressedEvent)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReviewReceived(review: Review) {
        if (review.rating == 0.0) {
            Toast.makeText(this, "Review must be higher than 0", Toast.LENGTH_SHORT).show()
        } else {
            intent.putExtra("review", review)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
