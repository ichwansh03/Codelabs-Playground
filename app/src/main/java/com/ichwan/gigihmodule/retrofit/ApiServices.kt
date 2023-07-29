package com.ichwan.gigihmodule.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    fun getUsers(): Call<ArrayList<ResponseModel>>
}