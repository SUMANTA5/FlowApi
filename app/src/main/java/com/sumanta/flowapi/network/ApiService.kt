package com.sumanta.flowapi.network

import com.sumanta.flowapi.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost(): List<Post>


}