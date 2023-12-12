package com.sample.android.todolistapp.domain.foundation

data class ResultEntity<T>(
    val data: T? = null,
    val error: ErrorEntity? = null
)