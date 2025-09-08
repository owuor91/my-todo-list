package com.akirachix.todos.repository

import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.api.ApiInterface
import com.akirachix.todos.model.ApiTodoItem
import com.akirachix.todos.model.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class TodoRepository {
    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun fetchTodos(): Response<List<TodoItem>> {
        return withContext(Dispatchers.IO) {
            suspendCancellableCoroutine { continuation ->
                val call = retrofit.getTodos()
                continuation.invokeOnCancellation { call.cancel() }

                val callback = object : Callback<List<ApiTodoItem>> {
                    override fun onResponse(call: Call<List<ApiTodoItem>>, response: Response<List<ApiTodoItem>>) {
                        if (response.isSuccessful) {
                            val todoItems = response.body()?.map { apiTodo ->
                                TodoItem(
                                    id = apiTodo.id,
                                    title = apiTodo.title,
                                    completed = apiTodo.completed
                                )
                            } ?: emptyList()
                            continuation.resume(Response.success(todoItems))
                        } else {
                            continuation.resume(Response.error(response.code(), response.errorBody()))
                        }
                    }

                    override fun onFailure(call: Call<List<ApiTodoItem>>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                }

                call.enqueue(callback)
            }
        }
    }
}