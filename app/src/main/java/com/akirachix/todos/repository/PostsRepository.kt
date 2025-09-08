package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Post

class PostsRepository {
    suspend fun getPosts(): List<Post> {
        return ApiInterface.getPosts()
    }
}