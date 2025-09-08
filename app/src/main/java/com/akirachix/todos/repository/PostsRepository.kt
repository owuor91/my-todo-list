package com.akirachix.todos.repository


import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository {
    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun fetchPosts(): Response<List<Post>> {
        return withContext(Dispatchers.IO) {
            retrofit.fetchPosts()
        }
    }
}