package com.mju.deliveryservice.data.model.cart

import com.google.gson.annotations.SerializedName

data class AddMenuToCartRequestDTO(
    @SerializedName("quantity")
    val quantity: Int
)
