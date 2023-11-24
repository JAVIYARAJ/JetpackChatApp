package com.example.jetpackdesign.data.model.common

sealed class Response<T>(val data: Any? = null, val message: String? = null) {
    class Success<T>(data: T?) : Response<T>(data = data)

    class Error<T>(message: String?) : Response<T>(message = message)
}