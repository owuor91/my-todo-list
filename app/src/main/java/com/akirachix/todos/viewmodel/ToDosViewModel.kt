package com.akirachix.todos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.ToDos
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.TodosRepository
import kotlinx.coroutines.launch

class TodosViewModel: ViewModel() {
    val todosRepository= TodosRepository()
    val todos= MutableLiveData<List<ToDos>>()
    val uiState= MutableLiveData(UiState())

    fun fetchTodos() {
        viewModelScope.launch {
            uiState.value=uiState.value?.copy(isLoading = true)
            val response = todosRepository.fetchTodos()
            if (response.isSuccessful){
                uiState.value=uiState.value?.copy(isLoading = false,
                    success = "Todos fetched successfully")
                todos.value=response.body()
            }else{
                uiState.value= uiState.value?.copy(isLoading = false,
                    error = response.errorBody()?.string())
            }
        }
    }
}