package com.example.pricer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pricer.constants.RequestCodes
import com.example.pricer.models.User
import com.google.android.material.textfield.TextInputEditText

class WelcomeActivity : AppCompatActivity() {

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var signIn: Button
    private lateinit var forgotPass: TextView
    private lateinit var fblogin: RelativeLayout
    private lateinit var googleLogin: RelativeLayout
    private lateinit var guestLogin: RelativeLayout
    private lateinit var signUp: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        bindViews()

        signIn.setOnClickListener {
            val emailVal = email.text.toString()
            val passVal = password.text.toString()
            if (emailVal.isEmpty() || passVal.isEmpty())
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show()
            else {

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

    private fun bindViews() {
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        signIn = findViewById(R.id.btn_sign_in)
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
}
