package com.akirachix.todos.appui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card // You already have this
import androidx.compose.material3.CardDefaults // <<< ADD OR ENSURE THIS LINE IS PRESENT
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akirachix.todos.model.Todo


@Composable
fun TodoItem(todo: Todo) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 6.dp, horizontal = 12.dp),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp) // This line uses CardDefaults
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Checkbox(checked = todo.completed, onCheckedChange = null)
      Spacer(Modifier.width(12.dp))
      Text(
        text = todo.title,
        color = Color.Black
      )
    }
  }
}
