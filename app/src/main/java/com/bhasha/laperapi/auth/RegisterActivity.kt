package com.bhasha.laperapi.auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bhasha.laperapi.Data.SignUpModel
import com.bhasha.laperapi.R
import com.bhasha.laperapi.api.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var login: TextView
    private lateinit var email: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var submitBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        textView = findViewById(R.id.user_text)
        login = findViewById(R.id.sign_in_btn)
        email = findViewById(R.id.user_email)
        name = findViewById(R.id.user_name)
        password = findViewById(R.id.user_password)
        submitBtn = findViewById(R.id.submit_btn)


        submitBtn.setOnClickListener {

            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()
            val userName = name.text.toString().trim()
            val user = SignUpModel(userEmail, userName, userName, userPassword)

            ResponseBody.signUpResponseBody(user, onResponse = { msg ->
                if (msg != null) {
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }
            },
                onFailure = { t ->
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                })


            login
                .setOnClickListener {
                    val intent = Intent(baseContext, LoginActivity::class.java)
                    startActivity(intent)
                }


        }

    }


}