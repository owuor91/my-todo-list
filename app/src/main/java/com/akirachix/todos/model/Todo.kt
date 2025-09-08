package com.akirachix.todos.model

data class Todo(
    var id: Int,
    var userId: Int,
    var title: String,
    var completed: Boolean
)
