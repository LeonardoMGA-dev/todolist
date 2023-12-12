package com.sample.android.todolistapp.data.repository

import com.sample.android.todolistapp.data.networking.Api
import com.sample.android.todolistapp.domain.entity.TodoEntity
import com.sample.android.todolistapp.domain.foundation.ErrorEntity
import com.sample.android.todolistapp.domain.foundation.ResultEntity
import com.sample.android.todolistapp.domain.repository.TodoRepository
import kotlinx.coroutines.*
import java.io.IOException
import java.net.ConnectException

class TodoRepositoryImp(
    private val api: Api,
) : TodoRepository {
    override suspend fun getTodos(): ResultEntity<List<TodoEntity>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                api.getTodos()
            }
            ResultEntity(data = response.map {
                TodoEntity(
                    userId = it.userId,
                    id = it.id,
                    title = it.title,
                    completed = it.completed,
                )
            })
        }catch (e: IOException) {
            ResultEntity(error = ErrorEntity.Network(e))
        } catch (e: Exception) {
            ResultEntity(error = ErrorEntity.Unknown(e))
        }
    }
}