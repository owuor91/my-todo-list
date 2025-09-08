package com.akirachix.todos.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue


object ApiInterface {
   val api: TODOApiservice by lazy {
       Retrofit.Builder()
           .baseUrl("https://jsonplaceholder.typicode.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(TODOApiservice::class.java)
   }
}

