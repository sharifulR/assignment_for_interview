package com.example.assignmentapp.model

class ProductModel : ArrayList<ProductModel.ProductModelItem>(){
    data class ProductModelItem(
        val id: Int,
        val title: String,
        val price: Double,
        val description: String,
        val category: String,
        val image: String,
        val rating: Rating
    ) {
        data class Rating(
            val rate: Double,
            val count: Int
        )
    }
}