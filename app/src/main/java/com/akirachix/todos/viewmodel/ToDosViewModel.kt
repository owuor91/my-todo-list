package com.akirachix.todos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.todos.model.ToDos
import com.akirachix.todos.model.UiState
import com.akirachix.todos.repository.ToDosRepository
import kotlinx.coroutines.launch

class ToDosViewModel : ViewModel(){
    val todosRepository = ToDosRepository()
    val posts = MutableLiveData<List<ToDos>>()


    val uiState = MutableLiveData(UiState())


    val post= MutableLiveData<ToDos>()


    fun fetchToDos(){
        viewModelScope.launch {
            uiState.value = uiState.value?.copy(isLoading = true)
            val response = todosRepository.fetchPosts()
            if (response.isSuccessful){
                uiState.value = uiState.value?.copy(isLoading = false,
                    success = "Post fetched successfully")
                posts.value = response.body()
            }else{
                uiState.value = uiState.value?.copy(isLoading = false,

                    error = response.errorBody()?.string())
            }
        }
    }
    fun fetchToDosById(todosId: Int){
        viewModelScope.launch {
            uiState.postValue(uiState.value?.copy(isLoading = true))
            val response = todosRepository.fetchPostById(todosId)
            if (response.isSuccessful){
                uiState.value=uiState.value?.copy(isLoading = false, success = "Fetch todos Successfully")
                post.postValue(response.body())

            }
            else{
                uiState.value=uiState.value?.copy(isLoading = false, error = response.errorBody()?.string())
            }
        }

    }
}