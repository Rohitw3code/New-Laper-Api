package com.bhasha.laperapi

import com.bhasha.laperapi.Data.SignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

//    @GET("api")
//    fun fetchData(): Call<ApiResponse>

    @POST("signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<SignUpRequest>


}