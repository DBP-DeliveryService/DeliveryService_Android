package com.mju.deliveryservice.data.model.order
import com.google.gson.annotations.SerializedName


data class OrderRequestDTO(
    @SerializedName("cartIdList") val cartIdList: List<Int>,
    @SerializedName("coupon_id") val couponId: Int,
    @SerializedName("orderStatus") val orderStatus: String,
    @SerializedName("payment_method") val paymentMethod: String,
    @SerializedName("requestMsg") val requestMsg: String,
    @SerializedName("totalPrice") val totalPrice: String
)