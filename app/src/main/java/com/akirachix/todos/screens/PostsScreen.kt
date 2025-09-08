package com.akirachix.todos.screens

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.model.Post
import com.akirachix.todos.model.UiState
import com.akirachix.todos.ui.theme.viewmodel.PostsViewModel


@Composable
fun PostsScreen(viewModel: PostsViewModel = viewModel()) {
    val posts by viewModel.posts.observeAsState()
    val uiState by viewModel.uiState.observeAsState(UiState())

    var isLoading by remember { mutableStateOf(true) }
    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (posts != null) {
        LazyColumn {
            items(posts!!) { post ->
                PostCard(post)
            }
        }
    } else if (uiState.error != null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = uiState.error.toString())
        }
    }
}

@Composable
fun PostCard(post: Post) {
    var isChecked by remember { mutableStateOf(post.isChecked) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = post.text, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Checkbox(
                checked = isChecked,
                onCheckedChange = { checked ->
                    isChecked = checked
                    post.isChecked = checked
                }
            )
        }
    }
}