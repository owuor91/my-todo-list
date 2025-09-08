package com.akirachix.todos.repository

import com.akirachix.todos.api.TodoApi

class TodoRepository(private val api: TodoApi) {
    suspend fun getTodos() = api.getTodos()
}
