package com.sample.android.todolistapp

import com.sample.android.todolistapp.data.networking.Api
import com.sample.android.todolistapp.data.repository.TodoRepositoryImp
import com.sample.android.todolistapp.domain.entity.TodoEntity
import com.sample.android.todolistapp.domain.foundation.ErrorEntity
import com.sample.android.todolistapp.domain.foundation.ResultEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class TodoRepositoryTest {


    @RelaxedMockK
    private lateinit var api: Api

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @After
    fun tearDown() {
    }

    @Test
    fun `test getTodos when success`() {
        // given
        val repository = TodoRepositoryImp(api)
        coEvery { api.getTodos() } returns emptyList()
        // when
        val result = runBlocking { repository.getTodos() }
        // then
        assertEquals(result, ResultEntity(emptyList<TodoEntity>()))
    }

    @Test
    fun `test getTodos when unknown error`() {
        val repository = TodoRepositoryImp(api)
        coEvery { api.getTodos() } throws Exception()
        // when
        val result = runBlocking { repository.getTodos() }
        // then
        assert(result.error is ErrorEntity.Unknown)
    }

    @Test
    fun `test getTodos when connection error`() {
        val repository = TodoRepositoryImp(api)
        coEvery { api.getTodos() } throws IOException()
        // when
        val result = runBlocking { repository.getTodos() }
        // then
        assert(result.error is ErrorEntity.Network)
    }
}