package com.bhasha.laperapi.api

import android.content.Context
import com.bhasha.laperapi.Data.LoginModel
import com.bhasha.laperapi.Data.LoginResponse
import com.bhasha.laperapi.Data.SignUpModel
import com.bhasha.laperapi.Data.UserBase
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

    fun getUserResponseBody(context: Context, onResponse: (UserBase?) -> Unit, onFailure: (Throwable) -> Unit) {
        val token = RetrofitClient.getCredential("token",context)
        val jsonapi = RetrofitClient.getClient()
        jsonapi.getUserData(token).enqueue(object : Callback<UserBase> {
            override fun onResponse(call: Call<UserBase>, response: Response<UserBase>) {
                if (response.isSuccessful) {
                    val userFetch: UserBase? = response.body()
                    onResponse(userFetch)
                } else {
                    onFailure(Throwable("Response unsuccessful"))
                }
            }

            override fun onFailure(call: Call<UserBase>, t: Throwable) {
                onFailure(t)
            }
        })
    }


}