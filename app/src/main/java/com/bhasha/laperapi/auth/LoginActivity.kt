package com.bhasha.laperapi.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bhasha.laperapi.api.ApiInterface
import com.bhasha.laperapi.Data.LoginModel
import com.bhasha.laperapi.Data.LoginResponse
import com.bhasha.laperapi.MainActivity
import com.bhasha.laperapi.R
import com.bhasha.laperapi.api.ResponseBody
import com.bhasha.laperapi.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var submitBtn: Button
    private lateinit var siginup: TextView
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_user_email)
        password = findViewById(R.id.login_user_password)
        submitBtn = findViewById(R.id.login_btn)
        siginup = findViewById(R.id.sign_up_btn)


        submitBtn.setOnClickListener {

            val user = LoginModel(email.text.toString().trim(), password.text.toString().trim())
            ResponseBody.logInResponseBody(user, onResponse = { token ->
                if (token != null) {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("token", token)
                    editor.apply()
                    Toast.makeText(baseContext, "welcome back", Toast.LENGTH_SHORT).show()
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }, onFailure = { t ->
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            })

        }

        siginup.setOnClickListener {
            val intent = Intent(baseContext, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        sharedPreferences = getSharedPreferences("credential", MODE_PRIVATE)
        if (sharedPreferences.getString("token", null) != null) {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }

    }

}