package com.mju.deliveryservice.data.model.cart
import com.google.gson.annotations.SerializedName


class CartDTO : ArrayList<CartDTOItem>()

data class CartDTOItem(
    @SerializedName("menuId") val menuId: Int,
    @SerializedName("menuContent") val menuContent: String,
    @SerializedName("menuName") val menuName: String,
    @SerializedName("price") val price: Int,
    @SerializedName("quantity") val quantity: Int
)
