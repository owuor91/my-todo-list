package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiInterface


class Todo(private val api: ApiInterface) {
    suspend fun fetchTodos(): List<Todo> = api.getTodos()
}

