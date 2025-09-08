package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TodoRepository {

        suspend fun getPosts(): List<Todo> {
            return ApiClient.apiInterface.getPosts()
        }

}