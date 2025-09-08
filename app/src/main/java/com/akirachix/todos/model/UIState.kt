package com.akirachix.todos.model

data class UIState(
  val isLoading: Boolean = false,
  val success: String? = "",
  val error: String? = ""
)
