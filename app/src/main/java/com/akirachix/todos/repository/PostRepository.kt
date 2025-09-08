package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostRepository {
    val retrofit = ApiClient.apiInterface(ApiInterface::class.java)
    suspend fun fetchPosts(): Response<List<Todo>> {
        return  withContext(Dispatchers.IO){
            retrofit.fetchPosts()
        }
    }
    suspend fun fetchPostById(postId: Int): Response<Todo>{
        return withContext(Dispatchers.IO){
            retrofit.fetchPostId(postId)
        }
    }
}