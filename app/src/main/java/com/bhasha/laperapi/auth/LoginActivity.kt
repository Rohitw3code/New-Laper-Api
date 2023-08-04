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

        val sharedPreferences: SharedPreferences = getSharedPreferences("credential", MODE_PRIVATE)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.235.135:3000/api/")
            .build()


        val jsonapi = retrofitBuilder.create(ApiInterface::class.java)


        submitBtn.setOnClickListener {
            Toast.makeText(baseContext, "email : "+email.text+" pass : "+password.text, Toast.LENGTH_SHORT).show()

            val user = LoginModel(email.text.toString().trim(), password.text.toString().trim())
            val call = jsonapi.logIn(user)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(baseContext, "welcome back : "+response.body(), Toast.LENGTH_SHORT).show()

                        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                        editor.putString("token", response.body()?.token)
                        editor.apply()
                        editor.commit()

                        val intent = Intent(baseContext,MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            baseContext,
                            "Fail " + response.message() + "",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
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