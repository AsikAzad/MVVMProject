package com.azad.mvvmproject.retrofit

import com.azad.mvvmproject.model.Blog
import retrofit2.http.GET

interface BlogRetrofit {
    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>

//    suspend fun add(blog: Blog)
}