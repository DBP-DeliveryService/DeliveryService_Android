package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.MessageResponse
import com.mju.deliveryservice.data.model.cart.AddMenuToCartRequestDTO
import com.mju.deliveryservice.data.model.cart.CartDTO
import com.mju.deliveryservice.data.model.coupon.CouponIssuanceRequestDTO
import com.mju.deliveryservice.data.model.order.OrderRequestDTO
import com.mju.deliveryservice.domain.model.CartItem
import com.mju.deliveryservice.domain.model.OrderData
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CartRepository {
    suspend fun getCart(): Result<List<CartItem>>

    suspend fun addMenuToCart(menuId: Int, quantity: Int): Result<String>

    suspend fun updateCart(
        menuId: Int,
        quantity: Int
    ): Result<String>

    suspend fun deleteCart(
       cartId: Int
    ): Result<String>

}