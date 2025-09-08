package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TodoRepository {
    private val api = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun fetchTodos(): Response<List<Todo>> {
        return withContext(Dispatchers.IO) {
            api.fetchTodos()
        }
    }
}



