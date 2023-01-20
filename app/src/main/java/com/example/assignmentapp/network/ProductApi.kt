package com.example.assignmentapp.network

import com.example.assignmentapp.Constants
import com.example.assignmentapp.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET(Constants.PRODUCTS_ENDPOINT)
    suspend fun getProducts(): Response<ProductModel>
}