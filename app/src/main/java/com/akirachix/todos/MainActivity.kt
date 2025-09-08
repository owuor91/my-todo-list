package com.akirachix.todos

import TodosScreen
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akirachix.todos.ui.theme.TodosTheme
import com.akirachix.todos.viewModel.TodoViewModel

class MainActivity : ComponentActivity() {
  private val todoViewModel = TodoViewModel()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TodosTheme {
        Surface(modifier = Modifier.fillMaxSize().safeContentPadding()) {
          TodosScreen(viewModel = todoViewModel)
        
        }
      }
    }
  }
}



