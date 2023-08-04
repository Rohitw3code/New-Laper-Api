package com.bhasha.laperapi.api

import com.bhasha.laperapi.Data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("user-fetch")
    fun getUserData(
        @Header("x-access-token") token:String,
    ): Call<UserFetch>

    @POST("signup")
    fun signUp(@Body signUpRequest: SignUpModel): Call<SignUpModel>

    @POST("login")
    fun logIn(@Body loginRequest: LoginModel): Call<LoginResponse>

}