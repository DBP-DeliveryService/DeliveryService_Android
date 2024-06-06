package com.mju.deliveryservice.domain.model

import com.google.gson.annotations.SerializedName

data class CartItem(
    val menuId: Int,
    val menuContent: String,
    val menuName: String,
    val price: Int,
    val quantity: Int
)
