package com.akirachix.todos.ui_file

import android.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akirachix.todos.model.Todo



@Composable
fun TodoItem(todo: Todo){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ){
            Checkbox(
                checked = todo.completed,
                onCheckedChange = null,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF6A1B9A),
                    uncheckedColor = Color.White,
                    checkmarkColor = Color(0xFF6650a4),


                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f)
            )
        }
    }
}