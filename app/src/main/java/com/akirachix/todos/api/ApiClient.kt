package com.akirachix.todos.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
  val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

  val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/") // Make sure base URL ends with /
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

  val apiInterface: ApiInterface by lazy {
    retrofit.create(ApiInterface::class.java)
  }
}
