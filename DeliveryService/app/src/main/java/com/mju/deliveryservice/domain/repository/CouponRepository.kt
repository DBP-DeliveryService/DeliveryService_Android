package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.MessageResponse
import com.mju.deliveryservice.data.model.coupon.CouponDTO
import com.mju.deliveryservice.data.model.coupon.CouponIssuanceRequestDTO
import com.mju.deliveryservice.domain.model.coupon.CouponEntity
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import retrofit2.Response
import retrofit2.http.Body

interface CouponRepository {
    suspend fun couponIssuance(couponCode: String): Result<String>

    suspend fun getCoupons(): Result<List<CouponEntity>>
}