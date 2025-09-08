package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiService
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.ui.TodoListScreen
import com.akirachix.todos.ui.theme.TodosTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodosTheme {
                TodoApp()
            }
        }
    }
}

@Composable
fun TodoApp() {
    val uiState = remember { mutableStateOf(UiState(isLoading = true)) }
    val todos = remember { mutableStateOf<List<Todo>>(emptyList()) }

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val apiService = ApiClient.buildApiClient(ApiService::class.java)
                todos.value = apiService.getTodos()
                uiState.value = UiState(isLoading = false, success = "Todos loaded", error = null)
            } catch (e: Exception) {
                uiState.value = UiState(isLoading = false, success = null, error = e.message)
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.value.isLoading -> {
                CircularProgressIndicator(
                    color = Color(0xFF2A4759),
                    modifier = Modifier.size(48.dp)
                )
            }
            uiState.value.error != null -> {
                Text(
                    text = "Error: ${uiState.value.error}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red,
                    fontSize = 16.sp
                )
            }
            uiState.value.success != null -> {
                TodoListScreen(todos = todos.value)
            }
        }
    }
}