package com.bhasha.laperapi.api

import com.bhasha.laperapi.Data.LoginModel
import com.bhasha.laperapi.Data.SignUpModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

//    @GET("api")
//    fun fetchData(): Call<ApiResponse>

    @POST("signup")
    fun signUp(@Body signUpRequest: SignUpModel): Call<SignUpModel>

    @POST("login")
    fun logIn(@Body loginRequest: LoginModel): Call<LoginModel>

}