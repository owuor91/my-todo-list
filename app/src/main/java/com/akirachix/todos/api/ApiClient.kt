package com.akirachix.todos.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
  private val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
  private val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
  private val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()
  fun <T> createService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}
