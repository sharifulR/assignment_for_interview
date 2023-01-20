package com.example.assignmentapp.network

import com.example.assignmentapp.Constants
import com.example.assignmentapp.model.LoginRequest
import com.example.assignmentapp.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

}