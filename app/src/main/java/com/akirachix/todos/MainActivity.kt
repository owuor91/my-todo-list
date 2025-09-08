package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.screens.TodoListScreen
import com.akirachix.todos.ui.theme.TodosTheme
import com.akirachix.todos.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TodosTheme {
        Surface(
          modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
            color = Color(0xFFD3D3D3)
        ) {
          val todoViewModel: TodoViewModel = viewModel()

          LaunchedEffect(Unit) {
            todoViewModel.fetchTodos()
          }

          TodoListScreen(
            todos = todoViewModel.todos.value,
            isLoading = todoViewModel.isLoading.value,
            errorMessage = todoViewModel.errorMessage.value,
            onRefresh = { todoViewModel.fetchTodos() }
          )
        }
      }
    }
  }
}