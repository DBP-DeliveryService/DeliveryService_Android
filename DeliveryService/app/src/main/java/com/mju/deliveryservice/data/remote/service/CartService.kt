package com.mju.deliveryservice.data.remote.service

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.MessageResponse
import com.mju.deliveryservice.data.model.cart.AddMenuToCartRequestDTO
import com.mju.deliveryservice.data.model.cart.CartDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CartService {
    @GET("/cart")
    suspend fun getCart(): Response<BaseResponse<CartDTO>>

    @POST("/cart/{menuId}")
    suspend fun addMenuToCart(
        @Path("menuId") menuId: Int,
        @Body body: AddMenuToCartRequestDTO
    ): Response<BaseResponse<MessageResponse>>

    @PATCH("/cart/{menuId}")
    suspend fun updateCart(
        @Path("menuId") menuId: Int,
        @Query("quantity") quantity: Int
    ): Response<BaseResponse<MessageResponse>>

    @DELETE("/cart/{cartId}")
    suspend fun deleteCart(
        @Path("cartId") cartId: Int
    ): Response<BaseResponse<MessageResponse>>
}