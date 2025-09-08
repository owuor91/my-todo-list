package com.akirachix.todos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.model.Post
import com.akirachix.todos.ui.theme.viewmodel.PostsViewModel

@Composable
fun PostsScreen() {
    val viewModel: PostsViewModel = viewModel()
    val posts = viewModel.posts.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.LightGray)
    ) {
        items(posts) { post ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = post.completed,
                    onCheckedChange = { viewModel.toggleCheckItem(post.id) }
                )
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    colors = androidx.compose.material3.CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = post.title,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
