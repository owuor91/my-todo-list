package com.akirachix.todos.api

import com.akirachix.todos.model.TODO
import retrofit2.http.GET

interface TODOApiservice {
    @GET("todos")
    suspend fun getTodos(): List<TODO>
}