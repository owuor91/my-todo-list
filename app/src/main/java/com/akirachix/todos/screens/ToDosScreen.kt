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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.model.ToDos
import com.akirachix.todos.model.UiState
import com.akirachix.todos.viewmodel.ToDosViewModel

@Composable
fun ToDosScreen(
    onClickToDos: (Int) -> Unit,
    viewModel: ToDosViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchToDos()
    }

    val todos by viewModel.posts.observeAsState()
    val uiState by viewModel.uiState.observeAsState(UiState())

    when {
        uiState.isLoading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        uiState.success != null -> {
            if (todos != null) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(todos!!) { todo ->
                        ToDosCard(todo, onClickToDos = onClickToDos)
                    }
                }
            }
        }
        uiState.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.error.toString())
            }
        }
    }
}

@Composable
fun ToDosCard(toDos: ToDos, onClickToDos: (Int) -> Unit) {
    Card(
        onClick = { onClickToDos(toDos.id) },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.3f),
                spotColor = Color.Black.copy(alpha = 0.5f)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(
                text = toDos.title,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Blue
            )
            Spacer(Modifier.height(8.dp))
            Text(text = "Completed: ${toDos.completed}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostCardPreview() {
    val todos = ToDos(
        user_id = 1,
        id = 2,
        title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        completed = true
    )
    ToDosCard(todos) { }
}

