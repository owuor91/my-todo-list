package com.akirachix.todos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.TodoItem
import com.akirachix.todos.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ChecklistViewModel : ViewModel() {
    private val repository = TodoRepository()
    private val _checklistItems = MutableStateFlow<List<TodoItem>>(emptyList())
    val checklistItems: StateFlow<List<TodoItem>> = _checklistItems

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            val response = repository.fetchTodos()
            if (response.isSuccessful) {
                _checklistItems.value = response.body() ?: emptyList()
            } else {
                _checklistItems.value = emptyList()
            }
        }
    }

    fun toggleCheckItem(itemId: Int) {
        _checklistItems.value = _checklistItems.value.map {
            if (it.id == itemId) it.copy(completed = !it.completed) else it
        }
    }
}