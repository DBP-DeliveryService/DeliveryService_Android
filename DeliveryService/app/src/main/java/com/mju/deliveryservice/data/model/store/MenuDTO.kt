package com.mju.deliveryservice.data.model.store

import com.google.gson.annotations.SerializedName

data class MenuDTO(
    @SerializedName("menuContent") val menuContent: String,
    @SerializedName("menuName") val menuName: String,
    @SerializedName("menuPictureUrl") val menuPictureUrl: String,
    @SerializedName("price") val price: Int
)