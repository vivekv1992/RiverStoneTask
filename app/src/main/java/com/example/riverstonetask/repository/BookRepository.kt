package com.example.riverstonetask.repository

import com.example.riverstonetask.model.Product
import com.example.riverstonetask.network.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class BookRepository(private val httpClient: HttpClient) {
    suspend fun getProduct(): ApiResponse<Product> {
        return try {
            val data: Product = httpClient.get("products/1").body()
            ApiResponse.Success(data)
        } catch (ex: Exception) {
            ApiResponse.Failure(ex)
        }
    }
}