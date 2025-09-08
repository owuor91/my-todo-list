package com.akirachix.todos.model

data class Post(
    val id: Int,
    val title: String,
    var completed: Boolean = false
)