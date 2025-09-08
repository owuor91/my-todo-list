package com.akirachix.todos.api

import com.akirachix.todos.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    suspend fun fetchPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
    suspend fun fetchPostById(@Path("postId") postId: Int): Response<Post>
    fun fetchPost(): Response<List<Post>>
    fun updatePost(postId: Int, completed: Boolean): Response<Unit>
}