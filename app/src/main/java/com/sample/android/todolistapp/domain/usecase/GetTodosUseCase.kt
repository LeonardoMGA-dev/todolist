package com.sample.android.todolistapp.domain.usecase

import com.sample.android.todolistapp.domain.entity.TodoEntity
import com.sample.android.todolistapp.domain.foundation.ResultEntity
import com.sample.android.todolistapp.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(): ResultEntity<List<TodoEntity>> {
        return repository.getTodos()
    }
}