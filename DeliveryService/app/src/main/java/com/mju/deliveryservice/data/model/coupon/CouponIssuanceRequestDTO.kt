package com.mju.deliveryservice.data.model.coupon

import com.google.gson.annotations.SerializedName

data class CouponIssuanceRequestDTO(
    @SerializedName("couponCode")
    val couponCode: String
)
