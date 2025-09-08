package com.akirachix.todos.model




data class TodoItem(
    val id: Int,
    val title: String,
    var completed: Boolean = false
)
