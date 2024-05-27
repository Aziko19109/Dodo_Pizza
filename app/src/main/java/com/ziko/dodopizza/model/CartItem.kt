package com.ziko.dodopizza.model

data class CartItem(
    var foodImage:String = "",
    val foodName: String = "",
    val foodPrice: String = "",
    var quantity: Int = 1
)