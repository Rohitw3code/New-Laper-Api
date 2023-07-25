package com.bhasha.laperapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val BASE_URL = "http://192.168.40.135:1234/"
class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.user_text)

        val apiInterface = RetrofitClient.getClient().create(ApiInterface::class.java)
        val call: Call<ApiResponse> = apiInterface.fetchData()


        baseContext.resources

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                textView.text = "resp : "+response.toString()
                if (response.isSuccessful) {
                    val apiResponse: ApiResponse? = response.body()
                    val message = apiResponse?.message ?: "No message available"

                    textView.text = "value : "+message
                    // Now you can use the 'message' variable which contains the "hello" message.
                    // For example, display it in a TextView or use it as per your requirement.
                } else {
                    // Handle unsuccessful responses here, if needed.
//                    textView.text = "Response is unsuccessful"
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle failures, such as network errors.
                textView.text = "Failed : "+t.message.toString()
            }
        })


//        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

//        val retrofitData = retrofitBuilder.getData()
//
//        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
//            override fun onResponse(
//                call: Call<List<MyDataItem>?>,
//                response: Response<List<MyDataItem>?>
//            ) {
//
//                val mytext = StringBuilder()
//                val responseBody = response.body()!!
//                for(myData in responseBody){
//                    mytext.append(myData.id)
//                    mytext.append("\n")
//                }
//
//                textView.text = "New "+response
//
//
//            }
//            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
//                textView.text = "Failed "+t.message
//            }
//        })





    }

}