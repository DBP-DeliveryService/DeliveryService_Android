package com.mju.deliveryservice.domain.model.category

import com.google.gson.annotations.SerializedName

data class StoresByCategory(
    val deliveryTip: Int,
    val detailCategoryName: String,
    val detailOpreateStatus: String,
    val minDeliveryTime: Int,
    val minPrice: Int,
    val rating: Int,
    val storeName: String
)
