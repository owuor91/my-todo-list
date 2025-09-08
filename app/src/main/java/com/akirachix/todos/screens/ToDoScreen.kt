package com.akirachix.todos.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.model.ToDo
import com.akirachix.todos.model.UIState
import com.akirachix.todos.viewModel.ToDosViewModel

@Composable
fun ToDoScreen(viewmodel: ToDosViewModel = viewModel()){
    val todos by viewmodel.todos.observeAsState()
    val uiState by viewmodel.uiState.observeAsState(UIState())

    LaunchedEffect(Unit) {
        viewmodel.fetchToDos()
    }

    when{
        uiState.isLoading ->{
            Box(Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        uiState.success != null ->{
            todos?.let { todoList ->
                LazyColumn (verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(todoList) { todo ->
                        ToDoCard(todo)
                    }
                }
            }
        }
        uiState.error != null ->{
            Box(Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                Text(text = uiState.error.toString())
            }
        }
    }
}

@Composable
fun ToDoCard(todo: ToDo){
    Card(modifier = Modifier.fillMaxWidth()) {
        Column (modifier = Modifier.fillMaxWidth().padding(16.dp,8.dp)){
            Text(text = todo.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = if (todo.completed) "Completed" else "Not Completed")
        }
    }
}
