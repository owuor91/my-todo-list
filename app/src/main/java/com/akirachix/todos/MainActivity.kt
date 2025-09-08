package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.Viewmodel.TodoViewModel
import com.akirachix.todos.ui.theme.Screens.TodoScreen
import com.akirachix.todos.ui.theme.TodosTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TodosTheme {
        Surface(modifier = Modifier.fillMaxSize().safeContentPadding()) {
          val todoViewModel: TodoViewModel = viewModel()
          val todos by todoViewModel.todos.observeAsState(initial = emptyList())
          TodoScreen(todos)
        }
      }
    }
  }
}
