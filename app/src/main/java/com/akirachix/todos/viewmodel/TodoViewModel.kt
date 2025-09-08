package com.akirachix.todos.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.Todo
import com.akirachix.todos.repository.TodoRepository
import kotlinx.coroutines.launch


class TodoViewModel : ViewModel() {
    private val repository = TodoRepository()
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    fun fetchTodos() {
        viewModelScope.launch {
            try {
                val todoList = repository.getTodos()
                _todos.postValue(todoList)
            } catch (e: Exception) {
                // Handle error
                Log.e("TodoViewModel", "Error fetching todos", e)
            }
        }
    }
}