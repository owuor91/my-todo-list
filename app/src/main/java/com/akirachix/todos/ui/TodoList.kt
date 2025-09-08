package com.akirachix.todos.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akirachix.todos.model.Todo

@Composable
fun TodoList(todos: List<Todo>, modifier: Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(todos) { todo ->
            TodoItem(todo = todo)
        }
    }
}