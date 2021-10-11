package com.azad.mvvmproject.di

import com.azad.mvvmproject.repository.MainRepository
import com.azad.mvvmproject.retrofit.BlogRetrofit
import com.azad.mvvmproject.retrofit.NetworkMapper
import com.azad.mvvmproject.room.BlogDao
import com.azad.mvvmproject.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}