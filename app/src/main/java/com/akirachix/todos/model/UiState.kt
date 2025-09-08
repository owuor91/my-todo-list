package com.akirachix.todos.model

data class UiState(
  val isLoading: Boolean = false,
  val success: String? = "",
  val error: String? = ""
)

data class Todo(
  val userId: Int,
  val id: Int,
  val title: String,
  val completed: Boolean
)
//) {
//  companion object {
//    val title: Any
//    val completed: Boolean
//  }
//}
