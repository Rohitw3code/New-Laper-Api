package com.bhasha.laperapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bhasha.laperapi.Data.User
import com.bhasha.laperapi.api.ResponseBody

class MainActivity : AppCompatActivity() {
    private lateinit var text: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.main_text)



        ResponseBody.getUserResponseBody(baseContext,
            onResponse = { json ->
                if (json != null) {
                    val user: User = json.user
                    text.text = json.user.name
                }
            },
            onFailure = { t ->
                t.printStackTrace()
            }
        )


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