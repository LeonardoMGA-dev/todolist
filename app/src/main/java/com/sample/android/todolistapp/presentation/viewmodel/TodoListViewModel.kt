package com.sample.android.todolistapp.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.android.todolistapp.domain.foundation.ErrorEntity
import com.sample.android.todolistapp.domain.usecase.GetTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TodoListUiState())
    val state = _state.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = getTodosUseCase()
            result.data?.let { todos ->
                _state.update { it.copy(todoList = todos) }
            }
            result.error?.let { _ ->
                handleError(result.error)
            }
        }.invokeOnCompletion {
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun handleError(errorEntity: ErrorEntity) {
        when (errorEntity) {
            is ErrorEntity.Network -> {
                _state.update { it.copy(error = errorEntity.exception?.message.orEmpty()) }
            }
            is ErrorEntity.Unknown -> {
                _state.update { it.copy(error = errorEntity.exception?.message.orEmpty()) }
            }
        }
    }
}