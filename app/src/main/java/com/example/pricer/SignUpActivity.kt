package com.example.pricer

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import com.example.pricer.events.RegisterEvent
import com.google.android.material.textfield.TextInputEditText
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SignUpActivity : AppCompatActivity() {

    private lateinit var back: ImageView
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var repeatPassword: TextInputEditText
    private lateinit var firstName: TextInputEditText
    private lateinit var lastName: TextInputEditText
    private lateinit var country: TextInputEditText
    private lateinit var city: TextInputEditText
    private lateinit var tos: CheckBox
    private lateinit var signUp: Button

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        bindViews()

        back.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        signUp.setOnClickListener {
            val emailVal = email.text.toString()
            val passVal = password.text.toString()
            val repeatPassVal = repeatPassword.text.toString()
            val firstNameVal = firstName.text.toString()
            val lastNameVal = lastName.text.toString()
            val countryVal = country.text.toString()
            val cityVal = city.text.toString()
            val isTosChecked = tos.isChecked

            if (emailVal.isBlank() || passVal.isBlank() || repeatPassVal.isBlank() || firstNameVal.isBlank() || lastNameVal.isBlank()
                || countryVal.isBlank() || cityVal.isBlank())
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show()
            else if (passVal.compareTo(repeatPassVal, false) != 0)
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            else if (!isTosChecked)
                Toast.makeText(this, "You need to agree to the terms", Toast.LENGTH_SHORT).show()
            else {
                user = User()
                user.email = emailVal
                user.password = passVal
                user.firstName = firstNameVal
                user.lastName = lastNameVal
                user.country = countryVal
                user.city = cityVal

                ApiCalls.registerUserEmail(this, user)
            }
        }
    }

    private fun bindViews() {
        back = findViewById(R.id.iv_back)
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        repeatPassword = findViewById(R.id.et_repeat_password)
        firstName = findViewById(R.id.et_first_name)
        lastName = findViewById(R.id.et_last_name)
        country = findViewById(R.id.et_country)
        city = findViewById(R.id.et_city)
        tos = findViewById(R.id.cb_tos_agree)
        signUp = findViewById(R.id.btn_sign_up)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRegisterEvent(registerEvent: RegisterEvent) {
        if (registerEvent.status == HttpStatus.SC_CREATED) {
            Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
            user.id = registerEvent.id
            val parentIntent = intent
            parentIntent.putExtra("user", user)
            setResult(Activity.RESULT_OK, parentIntent)
            finish()
        }
        else
            Toast.makeText(this, "Account already exists", Toast.LENGTH_SHORT).show()
    }
}
