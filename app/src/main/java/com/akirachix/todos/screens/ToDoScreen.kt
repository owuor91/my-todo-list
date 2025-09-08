package com.akirachix.todos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.Todo
import kotlinx.coroutines.launch

@Composable
fun TodoScreen() {
    val todos = remember { mutableStateListOf<Todo>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val apiTodos = ApiClient.retrofitService.getTodos()
                todos.clear()
                todos.addAll(apiTodos)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todos) { todo ->
                TodoItem(todo) { checked ->
                    val index = todos.indexOf(todo)
                    if (index != -1) {
                        todos[index] = todo.copy(completed = checked)
                    }
                }
            }
        }
    }
}

@Composable
fun TodoItem(todo: Todo, onCheckedChange: (Boolean) -> Unit) {
    Surface(
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
