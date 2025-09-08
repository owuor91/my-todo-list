package com.akirachix.todos.model



data class UiState(
  val posts: List<Post> = emptyList(),
  val isLoading: Boolean = false,
  val error: String? = null
)