package com.mju.deliveryservice.domain.model.store

import com.mju.deliveryservice.data.model.store.MenuDTO

data class StoreDetail(
    val deliveryTip: Int,
    val menuList: List<MenuDTO>,
    val minPrice: String,
    val rating: Int,
    val storeName: String
)
