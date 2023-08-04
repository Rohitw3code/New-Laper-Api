package com.bhasha.laperapi.api

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.227.135:3000/api/"

    private var retrofit: Retrofit? = null

    fun getClient(): ApiInterface {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(ApiInterface::class.java)
    }

    fun getCredential(field:String,context:Context):String{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("credential",
            AppCompatActivity.MODE_PRIVATE
        )
        return sharedPreferences.getString(field,"").toString()
    }

}
