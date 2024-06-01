package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.model.coupon.CouponIssuanceRequestDTO
import com.mju.deliveryservice.data.model.order.OrderRequestDTO
import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.CouponService
import com.mju.deliveryservice.data.remote.service.MyPageService
import com.mju.deliveryservice.data.remote.service.OrderService
import com.mju.deliveryservice.data.remote.service.StoreService
import com.mju.deliveryservice.domain.model.OrderData
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.domain.repository.CouponRepository
import com.mju.deliveryservice.domain.repository.MyPageRepository
import com.mju.deliveryservice.domain.repository.OrderRepository
import com.mju.deliveryservice.domain.repository.StoreRepository

class OrderRepositoryImpl: OrderRepository {
    private val service = RetrofitClient.getInstance().create(OrderService::class.java)

    override suspend fun doOrders(body: OrderData): Result<String> {
        val requestBody = body.run { OrderRequestDTO(cartIdList, couponId, orderStatus, paymentMethod, requestMsg, totalPrice) }
        val res = service.doOrders(requestBody)

        return try {
            if(res.isSuccessful){
                Result.success(res.body()?.data?.message ?: "Null Msg")
            } else {
                throw Exception("doOrders Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}