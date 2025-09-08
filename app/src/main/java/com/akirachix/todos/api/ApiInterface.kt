package com.akirachix.todos.api

import com.akirachix.todos.model.Todo
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    @GET("/todos")
    suspend fun fetchTodos(): Response<List<Todo>>

}