package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.MessageResponse
import com.mju.deliveryservice.data.model.coupon.CouponIssuanceRequestDTO
import com.mju.deliveryservice.data.model.order.OrderRequestDTO
import com.mju.deliveryservice.domain.model.OrderData
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import retrofit2.Response
import retrofit2.http.Body

interface OrderRepository {
    suspend fun doOrders(body: OrderData): Result<String>
}