package com.akirachix.todos
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.TodoApi
import com.akirachix.todos.repository.TodoRepository
import com.akirachix.todos.screens.TodoListScreen
import com.akirachix.todos.ui.theme.TodosTheme
import com.akirachix.todos.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    private val todoApi: TodoApi by lazy {
        ApiClient.buildApiClient(TodoApi::class.java)
    }
    private val todoRepository by lazy {
        TodoRepository(todoApi)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val todoViewModel = TodoViewModel(todoRepository)
        setContent {
            TodosTheme {
                Surface(modifier = Modifier.fillMaxSize().safeContentPadding()) {
                    val todosState = todoViewModel.todos.collectAsState()
                    TodoListScreen(todos = todosState.value)
                }
            }
        }
    }
}






