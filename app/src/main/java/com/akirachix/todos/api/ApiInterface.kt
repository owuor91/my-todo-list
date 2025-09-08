package com.akirachix.todos.api

import com.akirachix.todos.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun fetchPosts(): Response<List<Post>>
}