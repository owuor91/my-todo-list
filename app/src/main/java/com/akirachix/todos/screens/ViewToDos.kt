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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
//    val comments by viewModel.comments.observeAsState()


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        post?.let {
            Text(text = it.title)
            Spacer(Modifier.height(8.dp))
            Text(text = if (it.completed) "Completed" else "Not Completed")

        }
    }
}

//@Composable
//fun CommentRow(comment: Comment) {
//    Column (modifier = Modifier.padding(15.dp, 8.dp)){
//
//        Text(text = comment.name)
//        Spacer(Modifier.height(8.dp))
//        Text(text = comment.email)
//        Spacer(Modifier.height(8.dp))
//        Text(text = comment.body)
//        Spacer(Modifier.height(8.dp))
//        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
//    }
//}