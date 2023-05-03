package com.example.riverstonetask.ui.pages.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.riverstonetask.model.Product
import com.example.riverstonetask.network.ApiResponse
import com.example.riverstonetask.network.Resource
import com.example.riverstonetask.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookListViewModel(private val repository: BookRepository) : ViewModel() {
    private val _myStateFlow = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val myStateFlow: StateFlow<Resource<List<Product>>> = _myStateFlow
    init {
        fetchData()
    }
    fun fetchData() {
        _myStateFlow.value = Resource.Loading()
        viewModelScope.launch {
            when (val response = repository.getProduct()) {
                is ApiResponse.Success -> {
                    _myStateFlow.value = Resource.Success(arrayListOf(response.data))
                }
                is ApiResponse.Failure -> {
                    _myStateFlow.value = Resource.Failure(response.error.message!!)
                }
            }
        }
    }
}