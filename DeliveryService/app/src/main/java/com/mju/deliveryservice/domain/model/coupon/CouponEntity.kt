package com.mju.deliveryservice.domain.model.coupon

import com.google.gson.annotations.SerializedName

data class CouponEntity(
    val couponContent: String,
    val couponName: String,
    val couponStatus: String,
    val discountAmount: Int,
    val expiredDate: Int,
    val id: Int,
    val minPrice: Int
)
