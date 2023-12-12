package com.sample.android.todolistapp.domain.foundation

sealed interface ErrorEntity  {
    data class Network(val exception: Throwable? = null) : ErrorEntity
    data class Unknown(val exception: Throwable? = null) : ErrorEntity
}