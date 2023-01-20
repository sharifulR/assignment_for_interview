package com.example.assignmentapp.model

import android.graphics.drawable.Drawable

data class CartModel(
    val name: String,
    val image: Int,
    val price: Double,
    val totalPrice: Double,
    var quantity: Int
)
