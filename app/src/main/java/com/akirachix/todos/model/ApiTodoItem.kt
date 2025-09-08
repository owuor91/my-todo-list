package com.akirachix.todos.model

data class ApiTodoItem(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
