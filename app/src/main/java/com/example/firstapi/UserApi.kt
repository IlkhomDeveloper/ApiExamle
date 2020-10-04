package com.example.firstapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("default/index/")
    fun getUsers() : Call<ResponseData<List<Item>>>

    @POST("image")
    fun getPics(@Body pic: String): Call<ResponseData<List<Pics>>>
}