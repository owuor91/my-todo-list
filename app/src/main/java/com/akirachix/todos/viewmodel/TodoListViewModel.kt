package com.akirachix.todos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.TodosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class TodoListViewModel : ViewModel() {
    private val repository = TodosRepository()
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val response = repository.fetchTodos()
                if (response.isSuccessful) {
                    response.body()?.let { todos ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            todos = todos,
                            error = null
                        )
                    } ?: run {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = "No data received"
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Failed to load todos: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }

    fun updateTodoChecked(todo: Todo, isChecked: Boolean) {
        _uiState.value = _uiState.value.copy(todos = _uiState.value.todos.map {
            if (it.id == todo.id) it.copy(completed = isChecked) else it
        })
    }
}