package com.azad.mvvmproject.repository

import com.azad.mvvmproject.model.Blog
import com.azad.mvvmproject.retrofit.BlogRetrofit
import com.azad.mvvmproject.retrofit.NetworkMapper
import com.azad.mvvmproject.room.BlogDao
import com.azad.mvvmproject.room.CacheMapper
import com.azad.mvvmproject.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun addBlog(blog: Blog): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        try {
            blogDao.insert(cacheMapper.mapToEntity(blog))
            emit(DataState.Success("Data added."))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}