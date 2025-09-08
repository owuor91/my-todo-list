package com.akirachix.todos.api

import com.akirachix.todos.model.Todo
import retrofit2.http.GET


interface ApiInterface {
    @GET("todos")
    suspend fun getTodo(): List<Todo>
}