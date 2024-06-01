package com.mju.deliveryservice.data.remote.service

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.MessageResponse
import com.mju.deliveryservice.data.model.order.OrderRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderService {
    @POST("/orders")
    suspend fun doOrders(
        @Body body: OrderRequestDTO
    ): Response<BaseResponse<MessageResponse>>
}