package com.akirachix.todos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.TodosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch




class TodosViewModel : ViewModel() {
    private val repository = TodosRepository()

    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private val _checkedStates = mutableMapOf<Int, Boolean>()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val response = repository.getTodos()
                if (response.isSuccessful) {
                    val list = response.body() ?: emptyList()
                    list.forEach { todo ->
                        _checkedStates[todo.id] = todo.completed
                    }
                    _todos.value = list
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        success = "Todos loaded"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Failed to load todos"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun toggleTodo(id: Int) {
        _checkedStates[id] = !_checkedStates[id]!! ?: false

        _todos.value = _todos.value.map { todo ->
            if (todo.id == id) {
                todo.copy(completed = _checkedStates[id] ?: false)
            } else {
                todo
            }
        }
    }

    fun isChecked(id: Int): Boolean {
        return _checkedStates[id] ?: false
    }
}


