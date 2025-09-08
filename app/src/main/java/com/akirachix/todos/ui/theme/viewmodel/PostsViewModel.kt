package com.akirachix.todos.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.Post
import com.akirachix.todos.repository.PostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val repository = PostsRepository()
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            _posts.value = repository.fetchPosts()
        }
    }

    fun toggleCheckItem(itemId: Int) {
        _posts.value = _posts.value.map {
            if (it.id == itemId) it.copy(completed = !it.completed) else it
        }
    }
}