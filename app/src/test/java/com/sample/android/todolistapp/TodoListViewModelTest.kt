package com.sample.android.todolistapp

import com.sample.android.todolistapp.domain.entity.TodoEntity
import com.sample.android.todolistapp.domain.foundation.ErrorEntity
import com.sample.android.todolistapp.domain.foundation.ResultEntity
import com.sample.android.todolistapp.domain.usecase.GetTodosUseCase
import com.sample.android.todolistapp.presentation.viewmodel.TodoListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TodoListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getTodosUseCase: GetTodosUseCase

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `test getTodos when unknown error`() {
        // given
        coEvery { getTodosUseCase() } returns ResultEntity(error = ErrorEntity.Unknown(Exception("Unknown Error")))
        val viewModel = TodoListViewModel(getTodosUseCase)
        assert(viewModel.state.value.error.isNotEmpty())
    }

    @Test
    fun `test getTodos when connection error`() {
        // given
        coEvery { getTodosUseCase() } returns ResultEntity(error = ErrorEntity.Network(Exception("Network Error")))
        val viewModel = TodoListViewModel(getTodosUseCase)
        assert(viewModel.state.value.error.isNotEmpty())
    }

    @Test
    fun `test getTodos when empty list`() {
        // given
        coEvery { getTodosUseCase() } returns ResultEntity(emptyList())
        val viewModel = TodoListViewModel(getTodosUseCase)
        assert(viewModel.state.value.todoList.isEmpty())
    }

    @Test
    fun `test getTodos when not empty list`() {
        // given
        coEvery { getTodosUseCase() } returns ResultEntity(listOf(TodoEntity(1, 1, "title", false)))
        val viewModel = TodoListViewModel(getTodosUseCase)
        assert(viewModel.state.value.todoList.isNotEmpty())
    }

}