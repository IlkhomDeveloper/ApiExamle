package com.example.firstapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val okHttpClient = OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://twitter.edu-smart.uz/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}