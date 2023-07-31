package com.bhasha.laperapi.auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bhasha.laperapi.api.ApiInterface
import com.bhasha.laperapi.Data.LoginModel
import com.bhasha.laperapi.R
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_user_email)
        password = findViewById(R.id.login_user_password)
        submitBtn = findViewById(R.id.login_btn)
        siginup = findViewById(R.id.sign_up_btn)


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.38.135:3000/api/")
            .build()


        val jsonapi = retrofitBuilder.create(ApiInterface::class.java)

        val user = LoginModel(email.text.toString(), password.text.toString())

        submitBtn.setOnClickListener {

            val call = jsonapi.logIn(user)
            call.enqueue(object : Callback<LoginModel> {
                override fun onResponse(
                    call: Call<LoginModel>,
                    response: Response<LoginModel>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(baseContext, "welcome back", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Fail " + response.message(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    Toast.makeText(baseContext, "Failed : " + t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }

        siginup.setOnClickListener {
            val intent = Intent(baseContext,RegisterActivity::class.java)
            startActivity(intent)
        }

    }

}