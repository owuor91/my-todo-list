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
            try {
                val response = repository.fetchPosts()
                if (response.isSuccessful) {
                    _posts.value = response.body() ?: emptyList()
                } else {
                    _posts.value = emptyList()
                }
            } catch (e: Exception) {
                _posts.value = emptyList()
            }
        }
    }
    fun toggleCheckItem(itemId: Int) {
        _posts.value = _posts.value.map {
            if (it.id == itemId) it.copy(completed = !it.completed) else it
        }
    }
}