package com.bhasha.laperapi.api

import android.content.Context
import com.bhasha.laperapi.Data.SignUpModel
import com.bhasha.laperapi.Data.UserFetch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ResponseBody {
//    fun getUser(context:Context): Call<UserFetch> {
//        val token = RetrofitClient.getCredential("token",context)
//        val jsonapi = RetrofitClient.getClient()
//        return jsonapi.getUserData(token)
//    }



    fun signUp(model:SignUpModel):Call<SignUpModel>{
        val jsonapi = RetrofitClient.getClient()
        return jsonapi.signUp(model)
    }

    fun getUserResponseBody(context: Context, onResponse: (UserFetch?) -> Unit, onFailure: (Throwable) -> Unit) {
        val token = RetrofitClient.getCredential("token",context)
        val jsonapi = RetrofitClient.getClient()
        jsonapi.getUserData(token).enqueue(object : Callback<UserFetch> {
            override fun onResponse(call: Call<UserFetch>, response: Response<UserFetch>) {
                if (response.isSuccessful) {
                    val userFetch: UserFetch? = response.body()
                    onResponse(userFetch)
                } else {
                    onFailure(Throwable("Response unsuccessful"))
                }
            }

            override fun onFailure(call: Call<UserFetch>, t: Throwable) {
                onFailure(t)
            }
        })
    }


}