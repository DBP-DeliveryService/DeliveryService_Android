package com.mju.deliveryservice.data.remote.service

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.MessageResponse
import com.mju.deliveryservice.data.model.coupon.CouponIssuanceRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CouponService {
    @POST("/coupons")
    suspend fun couponIssuance(
        @Body body: CouponIssuanceRequestDTO
    ): Response<BaseResponse<MessageResponse>>
}