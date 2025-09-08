package com.akirachix.todos.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.ToDo
import com.akirachix.todos.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val repository = TodoRepository(
        ApiClient.buildApiClient(ApiInterface::class.java)
    )

    val todos = mutableStateOf<List<ToDo>>(emptyList())
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