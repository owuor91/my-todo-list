package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.ToDo

class TodoRepository(private val api: ApiInterface) {
    suspend fun fetchTodos(): List<ToDo> = api.getTodos()
}