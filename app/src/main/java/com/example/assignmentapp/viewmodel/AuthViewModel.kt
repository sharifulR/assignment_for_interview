package com.example.assignmentapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentapp.model.LoginRequest
import com.example.assignmentapp.model.LoginResponse
import com.example.assignmentapp.network.AuthApi
import com.example.assignmentapp.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authApi: AuthApi): ViewModel(){

    private val _loginResponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginResponseLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = _loginResponseLiveData

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch{
            val response =authApi.login(loginRequest)

            if (response.isSuccessful && response.body() != null){
                _loginResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null){
                _loginResponseLiveData.postValue(NetworkResult.Error(response.message(), response.body()))
            } else{
                _loginResponseLiveData.postValue(NetworkResult.Error("Something went wrong!"))
            }
        }
    }


}