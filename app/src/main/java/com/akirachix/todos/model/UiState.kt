package com.akirachix.todos.model

data class UiState(
  val posts: List<Todo> = emptyList(),
  val isLoading: Boolean = false,
  val error: String? = null
)