package com.akirachix.todos.api

import com.akirachix.todos.model.Todo
import com.akirachix.todos.repository.TodoRepo
import retrofit2.http.GET

interface ApiInterface {
    @GET("/todos")
    suspend fun getTodos(): List<Todo>
}