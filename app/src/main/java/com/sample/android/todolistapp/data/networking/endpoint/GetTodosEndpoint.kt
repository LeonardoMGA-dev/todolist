package com.sample.android.todolistapp.data.networking.endpoint

import retrofit2.http.GET

interface GetTodosEndpoint {
    @GET(URL)
    suspend fun getTodos(): List<Response>

    data class Response(
        val userId: Int,
        val id: Int,
        val title: String,
        val completed: Boolean,
    )

    companion object {
        private const val URL = "/todos"
    }
}