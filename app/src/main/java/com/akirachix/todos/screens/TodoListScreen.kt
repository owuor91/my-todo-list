package com.akirachix.todos.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState

@Composable
fun TodoListScreen(uiState: UiState, onTodoCheckedChange: (Todo, Boolean) -> Unit) {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        if (uiState.isLoading) {
            Text(text ="Loading...")
        }
        else if(uiState.error != null){
            Text(text = "Error: ${uiState.error}")
        }
        else{
            LazyColumn{
                items(uiState.todos){todo ->
                    TodoItem(todo = todo, onCheckedChange = {isChecked -> onTodoCheckedChange(todo, isChecked)})
                }
            }
        }
    }
}

@Composable
fun TodoItem(todo: Todo, onCheckedChange: (Boolean) -> Unit) {
  Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = todo.completed,
            onCheckedChange = onCheckedChange
        )
        Text(text = todo.title)
    }
}
