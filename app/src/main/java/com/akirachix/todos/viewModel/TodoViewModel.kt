package com.akirachix.todos.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.TodoRepository
import kotlinx.coroutines.launch
class TodoViewModel : ViewModel() {
    private val repository = TodoRepository()

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun fetchTodos() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            val response = repository.fetchTodos()
            if (response.isSuccessful) {
                val list = response.body()
                Log.d("TodoViewModel", "Received todos count: ${list?.size}")
                _todos.postValue(list ?: emptyList())
                _uiState.value = UiState(success = "Todos loaded", isLoading = false)
            } else {
                Log.e("TodoViewModel", "API Error: ${response.errorBody()?.string()}")
                _uiState.value = UiState(error = response.errorBody()?.string(), isLoading = false)
            }
        }
    }
}