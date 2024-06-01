package com.mju.deliveryservice.domain.model.store

import com.mju.deliveryservice.data.model.store.MenuDetailDTO

data class StoreDetail(
    val deliveryTip: Int,
    val menuList: List<MenuDetailDTO>,
    val minPrice: String,
    val rating: Int,
    val storeName: String
)
