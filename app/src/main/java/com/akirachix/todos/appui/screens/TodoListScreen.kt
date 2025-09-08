package com.akirachix.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.TodoService
import com.akirachix.todos.appui.screens.TodoListScreen
import com.akirachix.todos.model.Todo
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val todos: List<Todo> = ApiClient.createService(TodoService::class.java).getTodos()
            setContent {
                TodoListScreen(todos = todos)
            }
        }
    }
}
