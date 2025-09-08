package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.Todo
import com.akirachix.todos.api.ApiInterface


class TodoRepository {
    val apiService = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun getTodos(): List<Todo> {
        return apiService.getTodos()
    }
}