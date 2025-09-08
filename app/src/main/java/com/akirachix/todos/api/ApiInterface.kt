package com.akirachix.todos.api

import com.akirachix.todos.model.Todo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>
    @GET("/todos/{postId}")
    suspend fun getPostById(@Path("todoId") todoId: Int): Response<Todo>
}