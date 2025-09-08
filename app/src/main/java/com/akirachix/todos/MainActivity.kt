package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.TodoApi
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.ui.TodoList
import com.akirachix.todos.ui.theme.TodosTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TodosTheme {
        Surface(modifier = Modifier.fillMaxSize().safeContentPadding()) {
          TodoScreen()
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class) // Add this annotation here
@Composable
fun TodoScreen() {
  var uiState by remember { mutableStateOf(UiState()) }

  LaunchedEffect(Unit) {
    loadTodos { newState ->
      uiState = newState
    }
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("My Todo List") }

      )
    }
  ) { innerPadding ->
    when {
      uiState.isLoading -> {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          CircularProgressIndicator()
        }
      }

      uiState.error != null -> {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          Text(text = "Error: ${uiState.error}")
        }
      }

      else -> {
        TodoList(
          todos = uiState.todos,
          modifier = Modifier.padding(innerPadding)
        )
      }
    }
  }
}

fun loadTodos(onResult: (UiState) -> Unit) {
  onResult(UiState(isLoading = true))

  val api = ApiClient.buildApiClient(TodoApi::class.java)
  api.getTodos().enqueue(object : Callback<List<Todo>> {
    override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
      if (response.isSuccessful) {
        onResult(UiState(todos = response.body() ?: emptyList()))
      } else {
        onResult(UiState(error = "Failed to load todos"))
      }
    }

    override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
      onResult(UiState(error = t.message ?: "Unknown error"))
    }
  })
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
  TodosTheme {
    TodoScreen()
  }
}
