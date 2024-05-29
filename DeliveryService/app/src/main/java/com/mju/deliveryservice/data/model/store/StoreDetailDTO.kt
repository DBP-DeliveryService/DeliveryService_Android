package com.mju.deliveryservice.data.model.store
import com.google.gson.annotations.SerializedName

data class StoreDetailDTO(
    @SerializedName("deliveryTip") val deliveryTip: Int,
    @SerializedName("menuList") val menuList: List<MenuDTO>,
    @SerializedName("minPrice") val minPrice: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("storeName") val storeName: String
)