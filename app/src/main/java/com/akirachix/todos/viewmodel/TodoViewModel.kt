package com.akirachix.todos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val _todo = MutableLiveData<List<Todo>>()
    val uiState = MutableLiveData(UiState())

    fun fetchTodos() {
        viewModelScope.launch {
            uiState.value = uiState.value?.copy(isLoading = true)
            try {
                val todoList = ApiClient.apiInterface.getTodos()
                _todo.value = todoList
                uiState.value = uiState.value?.copy(isLoading = false, success = "Todos fetched successfully")
            } catch (e: Exception) {
                uiState.value = uiState.value?.copy(isLoading = false, error = e.message)
            }
        }
    }


}