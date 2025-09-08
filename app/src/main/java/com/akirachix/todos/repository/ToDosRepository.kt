package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import com.akirachix.todos.api.ApiInterface

class ToDosRepository {
    private val  todosService = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun fetchTodos(): Response<List<ToDo>> {
        return withContext(Dispatchers.IO) {
            todosService.getTodos()
        }
    }
}
