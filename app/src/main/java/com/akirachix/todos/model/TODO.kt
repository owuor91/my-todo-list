package com.akirachix.todos.model

data class TODO(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)