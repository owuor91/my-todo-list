package com.akirachix.todos.model

data class ToDos(
    val user_id: Int,
    val id : Int,
    val title : String,
    val completed: Boolean,
)
