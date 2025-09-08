package com.akirachix.todos.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.viewmodel.ToDosViewModel

@Composable
fun ViewToDosScreen(todosId: Int, viewModel: ToDosViewModel= viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchToDosById(todosId)
        viewModel.fetchToDos()

    }
    val post by viewModel.post.observeAsState()



    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        post?.let {
            Text(text = it.title)
            Spacer(Modifier.height(8.dp))
            Text(text = if (it.completed) "Completed" else "Not Completed")

        }
    }
}

