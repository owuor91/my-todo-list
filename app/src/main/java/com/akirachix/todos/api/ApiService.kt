package com.akirachix.todos.api

import com.akirachix.todos.model.Todo
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}