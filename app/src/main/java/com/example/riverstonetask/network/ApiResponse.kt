package com.example.riverstonetask.network

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class Failure(val error: Exception) : ApiResponse<Nothing>()
}