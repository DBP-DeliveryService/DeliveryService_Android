package com.mju.deliveryservice.data.remote.service

import com.google.gson.annotations.SerializedName
import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.MessageResponse
import com.mju.deliveryservice.data.model.cart.AddMenuToCartRequestDTO
import com.mju.deliveryservice.data.model.cart.CartDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartService {
    @GET("/cart")
    suspend fun getCart(): Response<BaseResponse<CartDTO>>

    @POST("/cart/{menuId}")
    suspend fun addMenuToCart(
        @Path("menuId") menuId: Int,
        @Body body: AddMenuToCartRequestDTO
    ): Response<BaseResponse<MessageResponse>>
}