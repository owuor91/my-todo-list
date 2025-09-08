package com.akirachix.todos.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
  val apiInterface: ApiInterface by lazy {
    Retrofit.Builder()
      .baseUrl("https://jsonplaceholder.typicode.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(ApiInterface::class.java)
  }
}