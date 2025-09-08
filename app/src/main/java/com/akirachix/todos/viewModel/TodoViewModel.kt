package com.akirachix.todos.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.TodoRepository
import kotlinx.coroutines.launch
class TodoViewModel : ViewModel() {
    private val todoRepository = TodoRepository()
    val todos = MutableLiveData<List<Todo>>()
    val uiState = MutableLiveData(UiState())

    fun fetchTodos() {
        uiState.value = uiState.value?.copy(isLoading = true)
        viewModelScope.launch {
            val response = todoRepository.fetchTodos()
            if (response.isSuccessful) {
                uiState.value = uiState.value?.copy(
                    isLoading = false,
                    success = "Todos fetched successfully"
                )
                todos.value = response.body()
            } else {
                uiState.value = uiState.value?.copy(
                    isLoading = false,
                    error = response.errorBody()?.string()
                )
            }
        }
    }
}