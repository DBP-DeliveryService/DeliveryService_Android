package com.mju.deliveryservice.domain.model

import com.google.gson.annotations.SerializedName

data class OrderData(
    val cartIdList: List<Int>,
    val couponId: Int,
    val orderStatus: String,
    val paymentMethod: String,
    val requestMsg: String,
    val totalPrice: String
)
