package com.akirachix.todos.api

import com.akirachix.todos.model.ToDo
import retrofit2.http.GET
import retrofit2.Response

interface ApiInterface {
    @GET("/todos")
    suspend fun getTodos(): Response<List<ToDo>>

}