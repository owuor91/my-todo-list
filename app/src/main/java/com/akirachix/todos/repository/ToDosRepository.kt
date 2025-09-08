package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.ToDos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ToDosRepository {
    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)


    suspend fun fetchPosts(): Response<List<ToDos>> {
        return withContext(Dispatchers.IO){
            retrofit.fetchToDos()
        }

    }

    suspend fun fetchPostById(todosId: Int): Response <ToDos>{
        return  withContext(Dispatchers.IO){
            retrofit.fetchToDosById(todosId)
        }
    }

}