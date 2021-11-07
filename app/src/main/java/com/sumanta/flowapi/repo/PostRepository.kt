package com.sumanta.flowapi.repo

import com.sumanta.flowapi.model.Post
import com.sumanta.flowapi.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository {

    companion object {
        fun getPost(): Flow<List<Post>> = flow {
            val response = RetrofitBuilder.api.getPost()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}