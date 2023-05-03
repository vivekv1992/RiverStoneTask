package com.example.riverstonetask.network

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()

    data class Failure(val message: String) : Resource<Nothing>()

    data class Loading(val name: String = "LOADING") : Resource<Nothing>()
}