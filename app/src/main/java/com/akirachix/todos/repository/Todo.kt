package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo


class TodoRepo(private val api: ApiInterface) {
    suspend fun fetchTodos(): List<Todo> = api.getTodos()
}

