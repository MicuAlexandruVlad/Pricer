package com.example.pricer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.constants.RequestCodes
import com.example.pricer.events.GetResponseEvent
import com.example.pricer.models.User
import com.example.pricer.utils.ApiCalls
import com.example.pricer.utils.JsonUtils
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import cz.msebera.android.httpclient.HttpStatus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find

class WelcomeActivity : AppCompatActivity() {
    companion object {
        const val TAG = "WelcomeActivity"
    }

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var rememberMe: CheckBox
    private lateinit var signIn: Button
    private lateinit var forgotPass: TextView
    private lateinit var fblogin: RelativeLayout
    private lateinit var googleLogin: RelativeLayout
    private lateinit var guestLogin: RelativeLayout
    private lateinit var signUp: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        EventBus.getDefault().register(this)

        bindViews()

        signIn.setOnClickListener {
            val emailVal = email.text.toString()
            val passVal = password.text.toString()
            if (emailVal.isEmpty() || passVal.isEmpty())
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show()
            else {
                ApiCalls.authUser(User().also {
                    it.email = emailVal
                    it.password = passVal
                })
            }
        }

        guestLogin.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Guest Login")
                .setMessage("Doing so will not unlock all the features of the app. " +
                        "Would you like to continue ?")
                .setNegativeButton("NO") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("YES") {_, _ ->
                    val user = User()
                    user.isGuest = true
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("currentUser", user)
                    startActivity(intent)
                }
            builder.create()
            builder.show()
        }

        signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)

            startActivityForResult(intent, RequestCodes.SIGN_UP_REQ_CODE)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onResponseEvent(getResponseEvent: GetResponseEvent) {
        when (getResponseEvent.status) {
            HttpStatus.SC_OK -> {
                if (getResponseEvent.objType == ObjectType.OBJ_USER) {
                    if (getResponseEvent.action == Actions.USER_AUTH) {
                        val currentUser = User().let {
                            JsonUtils.jsonObjectToUser(getResponseEvent.jsonResponseObj)
                        }

                        if (rememberMe.isChecked) {
                            // TODO: Store the user obj in Room
                        }
                    }
                }
            }

            HttpStatus.SC_NOT_FOUND -> {
                Toast.makeText(this, "Credentials are incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindViews() {
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        signIn = findViewById(R.id.btn_sign_in)
        rememberMe = find(R.id.cb_remember_me)
        forgotPass = findViewById(R.id.tv_forgot_pass)
        fblogin = findViewById(R.id.rl_fb_login)
        googleLogin = findViewById(R.id.rl_google_login)
        guestLogin = findViewById(R.id.rl_guest_login)
        signUp = findViewById(R.id.tv_sign_up)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RequestCodes.SIGN_UP_REQ_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val user = data.getSerializableExtra("user") as User
                email.setText(user.email)
                password.setText(user.password)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        EventBus.getDefault().unregister(this)
    }
}
