package com.akirachix.todos.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo
import com.akirachix.todos.repository.TodoRepo
import kotlinx.coroutines.launch
import android.util.Log

class TodoViewModel : ViewModel() {
    private val repository = TodoRepo(
        ApiClient.buildApiClient(ApiInterface::class.java)
    )

    val todos = mutableStateOf<List<Todo>>(emptyList())
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    fun fetchTodos() {
        isLoading.value = true
        errorMessage.value = null
        viewModelScope.launch {
            try {
                val fetchedTodos = repository.fetchTodos()
                todos.value = fetchedTodos
                Log.d("TodoViewModel", "Fetched ${fetchedTodos.size} todos")
            } catch (e: Exception) {
                errorMessage.value = "Failed to load todos: ${e.message}"
                Log.e("TodoViewModel", "Error fetching todos", e)
            } finally {
                isLoading.value = false
            }
        }
    }
}