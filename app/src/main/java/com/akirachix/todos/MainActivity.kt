package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.screens.PostsScreen
import com.akirachix.todos.ui.theme.TodosTheme
import com.akirachix.todos.ui.theme.viewmodel.PostsViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TodosTheme {
        Surface(modifier = Modifier) {
          PostsScreen(viewModel = viewModel<PostsViewModel>())
        }
      }
    }
  }
}