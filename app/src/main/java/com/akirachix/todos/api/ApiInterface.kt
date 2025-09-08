package com.akirachix.todos.api

import com.akirachix.todos.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/todos")
    suspend fun fetchPosts(): Response<List<Post>>

    @GET ("/todos/{userId}")
    suspend fun fetchPostId(@Path("postId") postId: Int): Response<Post>
}