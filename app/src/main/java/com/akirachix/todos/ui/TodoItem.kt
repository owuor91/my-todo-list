package com.akirachix.todos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akirachix.todos.model.Todo

@Composable
fun TodoItem(todo: Todo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray.copy(alpha = 0.3f))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = todo.completed,
            onCheckedChange = null, // Read-only
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(
            text = todo.title,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                fontStyle = if (todo.completed) FontStyle.Italic else FontStyle.Normal,
                textDecoration = if (todo.completed) TextDecoration.LineThrough else TextDecoration.None
            ),
            modifier = Modifier.weight(1f)
        )
    }
}