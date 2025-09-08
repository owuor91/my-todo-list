package com.akirachix.todos.api

import android.adservices.adid.AdId
import com.akirachix.todos.model.ToDos
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/todos")
    suspend fun fetchToDos() : Response <List<ToDos>>

}