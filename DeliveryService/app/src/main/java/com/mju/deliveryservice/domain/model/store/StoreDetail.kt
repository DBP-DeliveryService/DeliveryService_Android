package com.mju.deliveryservice.domain.model.store


data class StoreDetail(
    val deliveryTip: Int,
    val menuList: List<MenuDetail>,
    val minPrice: String,
    val rating: Int,
    val storeName: String
)
