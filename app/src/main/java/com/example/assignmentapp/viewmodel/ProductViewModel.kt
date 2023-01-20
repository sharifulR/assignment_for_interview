package com.example.assignmentapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentapp.model.ProductModel
import com.example.assignmentapp.network.NetworkResult
import com.example.assignmentapp.network.ProductApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productApi: ProductApi): ViewModel() {

    private val _productResponseLiveData = MutableLiveData<NetworkResult<ProductModel>>()
    val productResponseLiveData: LiveData<NetworkResult<ProductModel>>
        get() = _productResponseLiveData

    fun getProducts(){
        viewModelScope.launch {
            val response = productApi.getProducts()

            if (response.isSuccessful && response.body() != null){
                _productResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null){
                _productResponseLiveData.postValue(NetworkResult.Error(response.message(), response.body()))
            } else{
                _productResponseLiveData.postValue(NetworkResult.Error("Something went wrong!"))
            }
        }
    }

}