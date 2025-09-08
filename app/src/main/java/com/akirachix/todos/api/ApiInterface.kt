package com.akirachix.todos.api

import retrofit2.http.GET
data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
interface ApiInterface {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
