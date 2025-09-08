package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TodosRepository {
    private val api: ApiInterface = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun getTodos(): Response<List<Todo>> {

        return withContext(Dispatchers.IO) {
            api.fetchTodos()
        }
    }
}