package com.akirachix.todos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.UiState
import com.akirachix.todos.ui.TodoItem
import com.akirachix.todos.ui.theme.TodosTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

  private val _uiState = MutableStateFlow(UiState())
  private val uiState: StateFlow<UiState> = _uiState

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    fetchTodos()
    setContent {
      TodosTheme {
        Surface(
          modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
        ) {
          TodoListScreen(uiState)
        }
      }
    }
  }

  private fun fetchTodos() {
    _uiState.value = UiState(isLoading = true)
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val api = ApiClient.buildApiClient(ApiInterface::class.java)
        val todos = api.getTodos()
        withContext(Dispatchers.Main) {
          _uiState.value = UiState(isLoading = false, todos = todos)
        }
      } catch (e: Exception) {
        withContext(Dispatchers.Main) {
          _uiState.value = UiState(isLoading = false, error = "Failed to load todos: ${e.message}")
          Toast.makeText(this@MainActivity, "Failed to load todos: ${e.message}", Toast.LENGTH_SHORT).show()
        }
      }
    }
  }
}

@Composable
fun TodoListScreen(uiState: StateFlow<UiState>, modifier: Modifier = Modifier) {
  val state = uiState.collectAsState().value
  if (state.isLoading) {
    CircularProgressIndicator(
      modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
  } else {
    LazyColumn(modifier = modifier) {
      items(state.todos) { todo ->
        TodoItem(todo)
      }
    }
  }
}
