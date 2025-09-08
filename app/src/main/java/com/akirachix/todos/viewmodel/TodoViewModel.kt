package com.akirachix.todos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UIState
import com.akirachix.todos.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    val todoRepository = TodoRepository()
    val todos = MutableLiveData<List<Todo>>()
    val uiState = MutableLiveData(UIState())

    fun fetchTodos() {
        viewModelScope.launch {
            uiState.value = uiState.value?.copy(isLoading = true)
            val response = todoRepository.fetchTodos()
            if (response.isSuccessful) {
                uiState.value = uiState.value?.copy(success = "Fetch todos successfully", isLoading = false)
                todos.postValue(response.body())
            } else {
                uiState.value = uiState.value?.copy(error = response.errorBody()?.string(), isLoading = false)
            }
        }
    }
}