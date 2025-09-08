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
//    val posts: LiveData<List<Post>> = _posts

    val uiState = MutableLiveData(UiState())

    //    val uiState: LiveData<UIState> = _uiState
    val post= MutableLiveData<ToDos>()
//    val comments= MutableLiveData<List<Comment>>()

    fun fetchToDos(){
        viewModelScope.launch {
            uiState.value = uiState.value?.copy(isLoading = true)
//            val response = todosRepository.fetchToDos()
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
//    fun fetchComments(postId: Int){
//        viewModelScope.launch {
//            uiState.value=uiState.value?.copy(isLoading = true)
//            val response=todosRepository.fetchPostComments(postId)
//            if (response.isSuccessful){
//                comments.value=response.body()
//                uiState.value=uiState.value?.copy(isLoading = false, success = "Fetch comments Successfully")
//            }
//            else{
//                uiState.value=uiState.value?.copy(isLoading = false, error = response.errorBody()?.string())
//            }
//        }
//    }
}