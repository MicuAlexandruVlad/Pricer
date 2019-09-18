package com.example.pricer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.ProgressBar

class SplashActivity : AppCompatActivity() {

    private lateinit var loading: ProgressBar
    private lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loading = findViewById(R.id.pb_loading)
        logo = findViewById(R.id.iv_logo)

        val fadeInLoading = AlphaAnimation(0f, 1f)
        fadeInLoading.startOffset = 1000
        fadeInLoading.duration = 200
        loading.startAnimation(fadeInLoading)

        val fadeInLogo = AlphaAnimation(0f, 1f)
        fadeInLogo.duration = 500
        logo.startAnimation(fadeInLogo)

        fadeInLoading.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val intent = Intent(this@SplashActivity, WelcomeActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
