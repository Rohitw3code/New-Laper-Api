package com.bhasha.laperapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bhasha.laperapi.Data.SignUpRequest
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
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


        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.105.135:3000/api/")
            .build()
        // Create Service
        val service = retrofit.create(ApiInterface::class.java)

//        val apiInterface = RetrofitClient.getClient().create(ApiInterface::class.java)
        val jsonObject = JSONObject()
        jsonObject.put("email","beluga@gmail.com")
        jsonObject.put("name","beluga")
        jsonObject.put("username","beluga@")
        jsonObject.put("password","testing")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()


//        val call: Call<ApiResponse> = apiInterface.signUp("hel");
//        call.enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                textView.text = "resp : "+response.toString()
//                if (response.isSuccessful) {
//                    val apiResponse: ApiResponse? = response.body()
//                    val message = apiResponse?.message ?: "No message available"
//
//                    textView.text = "value : "+message
//                } else {
//                }
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                textView.text = "Failed : "+t.message.toString()
//            }
//        })


//        getMyData()

    }


    private fun pushUserDataToApi() {
//        val user = SignUpRequest("rohitcode005@gmail.com", "testing","test beta");


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