package com.akirachix.todos.model
data class UiState(
  val isLoading: Boolean = false,
  val todos: List<Todo> = emptyList(),
  val error: String? = null
)
