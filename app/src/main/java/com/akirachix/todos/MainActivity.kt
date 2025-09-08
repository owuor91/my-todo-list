package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.screens.TodoListScreen
import com.akirachix.todos.ui.theme.TodosTheme
import com.akirachix.todos.viewModel.TodoViewModel
import android.util.Log

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TodosTheme {
        Surface(
          modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
        ) {
          val todoViewModel: TodoViewModel = viewModel()

          val todos by todoViewModel.todos
          val isLoading by todoViewModel.isLoading
          val errorMessage by todoViewModel.errorMessage

          LaunchedEffect(todos) {
            Log.d("MainActivity", "Todos updated: ${todos.size}")
          }
          LaunchedEffect(Unit) {
            Log.d("MainActivity", "UI State: isLoading=$isLoading, error=$errorMessage")
          }

          LaunchedEffect(Unit) {
            Log.d("MainActivity", "Fetching todos...")
            todoViewModel.fetchTodos()
          }

          TodoListScreen(
            todos = todos,
            isLoading = isLoading,
            errorMessage = errorMessage,
            onRefresh = { todoViewModel.fetchTodos() }
          )
        }
      }
    }
  }
}