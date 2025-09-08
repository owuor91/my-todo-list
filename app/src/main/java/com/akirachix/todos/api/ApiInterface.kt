package com.akirachix.todos.api

import com.akirachix.todos.model.ApiTodoItem
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("todos")
    fun getTodos(): Call<List<ApiTodoItem>>


}

