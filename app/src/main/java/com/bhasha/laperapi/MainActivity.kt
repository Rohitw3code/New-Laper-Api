package com.bhasha.laperapi

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.bhasha.laperapi.Data.UserFetch
import com.bhasha.laperapi.Data.UserModel
import com.bhasha.laperapi.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.main_text)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.235.135:3000/api/")
            .build()

        val sharedPreferences: SharedPreferences = getSharedPreferences("credential", MODE_PRIVATE)
        val token = sharedPreferences.getString("token","").toString()

        val jsonapi = retrofitBuilder.create(ApiInterface::class.java)

        val call = jsonapi.getUserData(token)


        call.enqueue(object : Callback<UserFetch> {
            override fun onResponse(call: Call<UserFetch>, response: Response<UserFetch>) {
                if (response.isSuccessful) {
                    val userData = response.body()
                    text.text = userData.toString()
                } else {
                    // Handle error response here
                }
            }

            override fun onFailure(call: Call<UserFetch>, t: Throwable) {
                // Handle network error here
            }
        })



    }


}