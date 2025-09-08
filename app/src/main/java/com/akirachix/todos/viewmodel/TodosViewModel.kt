package com.akirachix.todos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.repository.TodoRepository
import kotlinx.coroutines.launch



class TodosViewModel: ViewModel() {
    val todosRepository = TodoRepository()
    val todos = MutableLiveData<List<Todo>>()
    val uiState= MutableLiveData(UiState())




    fun fetchTodos(){
        viewModelScope.launch {
uiState.value = uiState.value?.copy(isLoading = true)
            val response = todosRepository.fetchTodos()
            if (response.isSuccessful) {
                uiState.value = uiState.value?.copy(success = "fetch todos successfully", isLoading = false)
                todos.postValue(response.body())
            }
            else{
                uiState.value = uiState.value?.copy(isLoading = false,
                    error = response.errorBody()?.string())}
        }
    }
}