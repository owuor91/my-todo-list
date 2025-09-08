package com.akirachix.todos.viewMOdels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiService
import com.akirachix.todos.model.Todo
import com.akirachix.todos.repository.todoRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val repository = todoRepository(
        ApiClient.buildApiClient(ApiService::class.java)
    )

    val todos = mutableStateOf<List<Todo>>(emptyList())
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    fun fetchTodos() {
        isLoading.value = true
        errorMessage.value = null
        viewModelScope.launch {
            try {
                todos.value = repository.fetchTodos()
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
            isLoading.value = false
        }
    }
}