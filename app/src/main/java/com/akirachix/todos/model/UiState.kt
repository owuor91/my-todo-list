package com.akirachix.todos.model

data class UiState(
  val isLoading: Boolean = false,
  val success: String? = "",
  val error: String? = ""
)

