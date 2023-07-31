package com.bhasha.laperapi.auth

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bhasha.laperapi.Data.SignUpModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.40.135:1234/"
class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var email:EditText
    private lateinit var name:EditText
    private lateinit var password:EditText
    private lateinit var submitBtn:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.user_text)
        email = findViewById(R.id.user_email)
        name = findViewById(R.id.user_name)
        password = findViewById(R.id.user_password)
        submitBtn = findViewById(R.id.submit_btn)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.38.135:3000/api/")
            .build()


        val jsonapi = retrofitBuilder.create(ApiInterface::class.java)

        val user = SignUpModel(email.text.toString(), password.text.toString(),name.text.toString(),name.text.toString())

        submitBtn.setOnClickListener {

            val call = jsonapi.signUp(user)
            call.enqueue(object :Callback<SignUpModel>{
                override fun onResponse(
                    call: Call<SignUpModel>,
                    response: Response<SignUpModel>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(baseContext,"Registered",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(baseContext,"Fail "+response.message(),Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                    Toast.makeText(baseContext,"Failed",Toast.LENGTH_SHORT).show()
                }

            })


        }






    }


}