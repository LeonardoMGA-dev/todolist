package com.sample.android.todolistapp.presentation.viewmodel

import com.sample.android.todolistapp.domain.entity.TodoEntity

data class TodoListUiState(
    val isLoading: Boolean = false,
    val todoList: List<TodoEntity> = emptyList(),
    val error: String = ""
)