package com.akirachix.todos.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akirachix.todos.viewmodel.TodoViewModel

@Composable
fun AppNavigation() {
    TodoScreen(viewModel = viewModel())
}