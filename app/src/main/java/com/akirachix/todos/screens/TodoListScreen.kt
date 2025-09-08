@file:OptIn(ExperimentalMaterial3Api::class)

package com.akirachix.todos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.viewmodel.TodosViewModel

@Composable
fun TodoListScreen(viewModel: TodosViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchTodos()
    }

    val todos by viewModel.todos.observeAsState()
    val uiState by viewModel.uiState.observeAsState(UiState())

    Column(Modifier.fillMaxSize().background(Color(0xFFF5F2F7))) {
        TopAppBar(
            title = { Text("My Todo List", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFF6EFFF)
            )
        )

        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.success != null -> {
                if (todos != null) {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp, 8.dp, 16.dp, 8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(todos!!) { todo ->
                            TodoCard(todo)
                        }
                    }
                }
            }
            uiState.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = uiState.error.toString())
                }
            }
        }
    }
}

@Composable
fun TodoCard(todo: Todo) {
    val purple = Color(0xFF8B4DDF)
    var checked by remember { mutableStateOf(todo.completed) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF1F1F1),
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = purple,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = todo.title,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF222222),
                fontSize = 15.sp
            )
        }
    }
}
