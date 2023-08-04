package com.bhasha.laperapi

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bhasha.laperapi.Data.UserModel
import com.bhasha.laperapi.api.ResponseBody

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView
    private lateinit var logout:Button
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.main_text)
        logout = findViewById(R.id.main_logout)
        sharedPreferences = getSharedPreferences("credential", MODE_PRIVATE)



        ResponseBody.getUserResponseBody(baseContext,
            onResponse = { json ->
                if (json != null) {
                    val user: UserModel = json.user
                    text.text = json.user.name +" Date : "+user.date_created
                }
            },
            onFailure = { t ->
                Toast.makeText(baseContext,t.message, Toast.LENGTH_SHORT).show()
            }
        )

        logout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("token", null)
            editor.apply()
        }


    //        val call = FetchData.getUser(baseContext)

//        call.enqueue(object : Callback<UserFetch> {
//            override fun onResponse(call: Call<UserFetch>, response: Response<UserFetch>) {
//                if (response.isSuccessful) {
//                    val userData = response.body()
//                    text.text = userData.toString()
//                } else {
//                    // Handle error response here
//                }
//            }
//
//            override fun onFailure(call: Call<UserFetch>, t: Throwable) {
//                // Handle network error here
//            }
//        })



    }


}