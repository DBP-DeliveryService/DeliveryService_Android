package com.mju.deliveryservice.domain.model.store

import com.google.gson.annotations.SerializedName

data class MenuDetail(
    val menuContent: String,
    val menuName: String,
    val menuPictureUrl: String,
    val price: Int
)
