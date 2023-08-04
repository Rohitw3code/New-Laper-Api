package com.bhasha.laperapi.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.bhasha.laperapi.Data.LoginModel
import com.bhasha.laperapi.Data.LoginResponse
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


    fun logInResponseBody(model:LoginModel, onResponse: (String?) -> Unit, onFailure: (Throwable) -> Unit) {
        val jsonapi = RetrofitClient.getClient()
        jsonapi.logIn(model).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    onResponse(response.body()?.token)
                } else {
                    onFailure(Throwable("Response unsuccessful"))
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }

    fun signUpResponseBody(model:SignUpModel, onResponse: (String?) -> Unit, onFailure: (Throwable) -> Unit) {
        val jsonapi = RetrofitClient.getClient()
        jsonapi.signUp(model).enqueue(object : Callback<SignUpModel> {
            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                if (response.isSuccessful) {
                    onResponse("Sign Up Successful")
                } else {
                    onFailure(Throwable("Response unsuccessful"))
                }
            }
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                onFailure(t)
            }
        })
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