package com.akirachix.todos.api

import com.akirachix.todos.model.ToDo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/todos")
    suspend fun getTodos(): List<ToDo>

    @GET("/todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): ToDo

}

