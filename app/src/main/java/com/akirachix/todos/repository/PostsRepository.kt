package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}

class PostsRepository {
    suspend fun fetchPosts(): List<Post> {
        return withContext(Dispatchers.IO) {
            val response = ApiClient.apiService.fetchPosts()
            if (response.isSuccessful) {
                response.body()?.map { post ->
                    Post(id = post.id, title = post.title, completed = false)
                } ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}