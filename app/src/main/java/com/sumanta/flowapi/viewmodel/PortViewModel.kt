package com.sumanta.flowapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumanta.flowapi.model.Post
import com.sumanta.flowapi.repo.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PortViewModel : ViewModel() {

    private val postMutableLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    val postLiveData: LiveData<List<Post>>
        get() = postMutableLiveData


    fun getPost() {
        viewModelScope.launch {
            PostRepository.getPost()
                .catch { e ->
                    Log.d("main", "getPost: ${e.message}")
                }
                .collect { response ->
                    postMutableLiveData.value = response
                }
        }
    }
}