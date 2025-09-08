package com.akirachix.todos.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akirachix.todos.model.Todo
val LightPink = Color(0xFFFADCE1)
val LightGray = Color(0xFFE0E0E0)
@Composable
fun TodoListScreen(todos: List<Todo>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink)
            .padding(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(todos) { todo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = LightGray),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = todo.completed,
                        onCheckedChange = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = todo.title, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
