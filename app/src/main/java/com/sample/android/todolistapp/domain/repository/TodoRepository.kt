package com.sample.android.todolistapp.domain.repository

import com.sample.android.todolistapp.domain.entity.TodoEntity
import com.sample.android.todolistapp.domain.foundation.ResultEntity

interface TodoRepository {
    suspend fun getTodos(): ResultEntity<List<TodoEntity>>
}