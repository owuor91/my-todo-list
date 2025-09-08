package com.akirachix.todos.api

import com.akirachix.todos.model.ToDo
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    @GET("/todos")
    suspend fun fetchToDos(): Response<List<ToDo>>
}