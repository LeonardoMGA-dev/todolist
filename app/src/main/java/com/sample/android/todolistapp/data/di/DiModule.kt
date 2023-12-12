package com.sample.android.todolistapp.data.di

import com.sample.android.todolistapp.data.networking.Api
import com.sample.android.todolistapp.data.repository.TodoRepositoryImp
import com.sample.android.todolistapp.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DiModule {
    @Provides
    fun provideApi(): Api = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    @Provides
    fun provideTodoRepository(api: Api): TodoRepository = TodoRepositoryImp(api)

}