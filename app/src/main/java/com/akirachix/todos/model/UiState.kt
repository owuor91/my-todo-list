package com.akirachix.todos.model

import com.akirachix.todos.api.Todo

data class UiState(
  val isLoading: Boolean = false,
  val success: String? = "",
  val error: String? = "",
  val todos: List<Todo> = emptyList()
)
