package com.akirachix.todos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.PostRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val repository = PostRepository()
    private val _todo = MutableLiveData<List<Todo>>()
    val posts: LiveData<List<Todo>> = _todo

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun fetchPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(isLoading = true)
            val response = repository.fetchPosts()
            if (response.isSuccessful) {
                _todo.value = response.body()
                _uiState.value = _uiState.value?.copy(isLoading = false)
            } else {
                _uiState.value = _uiState.value?.copy(
                    isLoading = false,
                    error = response.errorBody()?.string()
                )
            }
        }
    }
}
