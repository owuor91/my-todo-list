package com.akirachix.todos.ui.theme.viewmodelimport

import androidx.lifecycle.ViewModel

androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.ToDo
import com.akirachix.todos.repository.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDosViewModel : ViewModel() {
    private val repository = ToDoRepository()
    private val _todo = MutableStateFlow<List<ToDo>>(emptyList())
    val posts: StateFlow<List<ToDo>> = _todo

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _todo.value = repository.fetchToDos()
        }
    }

    fun toggleCheckItem(itemId: Int) {
        _todo.value = _todo.value.map {
            if (it.id == itemId) it.copy(completed = !it.completed) else it
        }
    }
}