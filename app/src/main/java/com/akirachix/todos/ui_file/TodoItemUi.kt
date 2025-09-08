package com.akirachix.todos.ui_file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import kotlinx.coroutines.launch



@Composable
fun TodoListScreen() {
    var todos by remember { mutableStateOf<List<Todo>>(emptyList()) }
    var uiState by remember { mutableStateOf(UiState(isLoading = true)) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val api = ApiClient.buildApiClient(ApiInterface::class.java)
                todos = api.getTodos()
                uiState = UiState(isLoading = false, success = "Todos loaded successfully")
            } catch (e: Exception) {
                uiState = UiState(isLoading = false, error = e.message ?: "Unknown error")
            }
        }
    }

    Scaffold(
    ) { paddingValues ->

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.error != null && uiState.error!!.isNotBlank()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${uiState.error}")
            }
        } else {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .background(Color(0xFFCCC2DC)),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(todos) { todo ->
                    TodoItem(todo)
                }
            }
        }
    }
}