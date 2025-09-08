package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.Post

class PostsRepository {
    suspend fun getPosts(): List<Post> {
        return ApiClient.apiInterface.getPosts()
    }
}