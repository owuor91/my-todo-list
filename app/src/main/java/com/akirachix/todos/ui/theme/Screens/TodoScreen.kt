package com.akirachix.todos.ui.theme.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.akirachix.todos.model.TODO

@Composable
fun TodoScreen(todos: List<TODO>) {
    LazyColumn {
        items(todos) { todo ->
            ListItem(
                headlineContent = { Text(todo.title) },
                supportingContent = { Text("Completed: ${todo.completed}") }
            )
            Divider()
        }
    }
}