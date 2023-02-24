package com.samfonsec.hscards.domain.response

sealed class DataResponse<out T : Any> {
    class Success<out T : Any>(val data: T) : DataResponse<T>()
    class Error(val exception: Throwable) : DataResponse<Nothing>()
}