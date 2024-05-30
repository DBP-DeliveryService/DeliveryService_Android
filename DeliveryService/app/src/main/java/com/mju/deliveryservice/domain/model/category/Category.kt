package com.mju.deliveryservice.domain.model.category

import com.google.gson.annotations.SerializedName

data class Category(
    val categoryImgUrl: String,
    val categoryName: String,
    val id: Int
)
