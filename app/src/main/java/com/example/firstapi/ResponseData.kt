package com.example.firstapi

data class ResponseData<T>(
    val status:String,
    val message:String ="Successful",
    val data:T? = null
)