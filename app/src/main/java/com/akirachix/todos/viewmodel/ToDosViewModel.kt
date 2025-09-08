package com.akirachix.todos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.ToDo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.ToDosRepository
import kotlinx.coroutines.launch

class ToDosViewModel : ViewModel() {
    private val todosRepository = ToDosRepository()


    private val _todos = MutableLiveData<List<ToDo>>()
    val todos: LiveData<List<ToDo>> = _todos


    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        _uiState.value = _uiState.value?.copy(isLoading = true, error = null, success = null)

        viewModelScope.launch {
            val response = todosRepository.fetchTodos()
            if (response.isSuccessful) {
                _todos.value = response.body() ?: emptyList()
                _uiState.value = _uiState.value?.copy(
                    isLoading = false,
                    success = "My todo list has been fetched successfully",
                    error = null
                )
            } else {
                _uiState.value = _uiState.value?.copy(
                    isLoading = false,
                    error = "Error: ${response.code()} - ${response.errorBody()?.string() ?: "Unknown error"}",
                    success = null
                )
                _todos.value = emptyList()
            }
        }
    }
}
