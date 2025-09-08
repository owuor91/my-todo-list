package com.akirachix.todos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo
import kotlinx.coroutines.launch

@Composable
fun TodoScreen() {
    var todos by remember { mutableStateOf(listOf<Todo>()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val api = ApiClient.buildApiClient(ApiInterface::class.java)
                todos = api.getTodos()
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Failed to load todos. Please check your internet connection and try again."
            }
        }
    }

    if (errorMessage != null) {
        Text(
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(todos) { todo ->
                TodoItem(todo)
            }
        }
    }
}


@Composable
fun TodoItem(todo: Todo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(todo.title)
        }
    }
}
