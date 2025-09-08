package com.akirachix.todos.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.api.RetrofitInstance
import com.akirachix.todos.model.TODO
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    private val _todos = MutableLiveData<List<TODO>>()
    val todos: LiveData<List<TODO>> = _todos

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            try {
                _todos.value = RetrofitInstance.api.getTodos()
            } catch (e: Exception) {

            }
        }
    }
}

