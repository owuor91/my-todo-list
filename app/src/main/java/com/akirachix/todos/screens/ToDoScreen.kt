package com.akirachix.todos.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.model.Todo
import com.akirachix.todos.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    LaunchedEffect(Unit) { viewModel.fetchTodos() }
    val todos by viewModel.todos.observeAsState()
    val uiState by viewModel.uiState.observeAsState()
    when {
        uiState?.isLoading == true -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        uiState?.success != null -> if (todos != null) LazyColumn { items(todos!!) { todo -> TodoCard(todo) } }
        uiState?.error != null -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(text = uiState!!.error.toString()) }
    }
}

@Composable
fun TodoCard(todo: Todo) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = todo.completed, onCheckedChange = null, enabled = false, colors = CheckboxDefaults.colors(checkedColor = Color(0xFF800080)))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = todo.title, fontWeight = FontWeight.Bold)
        }
    }
}