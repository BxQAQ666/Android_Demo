package com.example.demo1.pojo

data class ResponseMessage<T>(
    val code: Int,
    val message: String,
    val data: T?
)