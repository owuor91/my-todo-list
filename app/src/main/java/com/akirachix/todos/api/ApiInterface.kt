package com.akirachix.todos.api

import com.akirachix.todos.model.Post
import retrofit2.http.GET

interface ApiInterface {
      @GET("todos")
    suspend fun getPosts(): List<Post>
}

