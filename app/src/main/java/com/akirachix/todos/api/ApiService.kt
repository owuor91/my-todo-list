package com.akirachix.todos.api

import com.akirachix.todos.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/todos")
    suspend fun getTodos(): List<Todo>

    @GET("/todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): Todo



}


