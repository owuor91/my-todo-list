package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.screens.TodoListScreen
import com.akirachix.todos.ui.theme.TodosTheme
import com.akirachix.todos.viewMOdels.TodoViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TodosTheme {
        Surface(modifier = Modifier.fillMaxSize().safeContentPadding()) {

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
