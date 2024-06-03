package com.mju.deliveryservice.data.model.coupon
import com.google.gson.annotations.SerializedName


data class CouponDTO(
    @SerializedName("couponResList") val couponResList: List<CouponResDTO>
)

data class CouponResDTO(
    @SerializedName("couponContent") val couponContent: String,
    @SerializedName("couponName") val couponName: String,
    @SerializedName("couponStatus") val couponStatus: String,
    @SerializedName("discountAmount") val discountAmount: Int,
    @SerializedName("expiredDate") val expiredDate: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("minPrice") val minPrice: Int
)