package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiService
import com.akirachix.todos.model.Todo

class todoRepository (private val api: ApiService){
    suspend fun fetchTodos(): List<Todo> = api.getTodos()

}
