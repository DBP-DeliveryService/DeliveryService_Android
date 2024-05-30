package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.model.cart.AddMenuToCartRequestDTO
import com.mju.deliveryservice.data.model.coupon.CouponIssuanceRequestDTO
import com.mju.deliveryservice.data.model.order.OrderRequestDTO
import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.CartService
import com.mju.deliveryservice.data.remote.service.CouponService
import com.mju.deliveryservice.data.remote.service.MyPageService
import com.mju.deliveryservice.data.remote.service.OrderService
import com.mju.deliveryservice.data.remote.service.StoreService
import com.mju.deliveryservice.domain.model.CartItem
import com.mju.deliveryservice.domain.model.OrderData
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.domain.repository.CartRepository
import com.mju.deliveryservice.domain.repository.CouponRepository
import com.mju.deliveryservice.domain.repository.MyPageRepository
import com.mju.deliveryservice.domain.repository.OrderRepository
import com.mju.deliveryservice.domain.repository.StoreRepository

class CartRepositoryImpl: CartRepository {
    private val service = RetrofitClient.getInstance().create(CartService::class.java)

    override suspend fun getCart(): Result<List<CartItem>> {
        val res = service.getCart()

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.map { CartItem(menuContent = it.menuContent, menuName = it.menuName, price = it.price, quantity = it.quantity) }

                if(data.isEmpty()) Result.success(listOf())
                else Result.success(data)

            } else {
                throw Exception("getCart Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun addMenuToCart(menuId: Int, quantity: Int): Result<String> {
        val res = service.addMenuToCart(menuId, AddMenuToCartRequestDTO(quantity))

        return try {
            if(res.isSuccessful){
                Result.success(res.body()?.data?.message ?: "Null Msg")
            } else {
                throw Exception("addMenuToCart Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun updateCart(menuId: Int, quantity: Int): Result<String> {
        val res = service.updateCart(menuId, quantity)

        return try {
            if(res.isSuccessful){
                Result.success(res.body()?.data?.message ?: "Null Msg")
            } else {
                throw Exception("updateCart Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun deleteCart(cartId: Int): Result<String> {
        val res = service.deleteCart(cartId)

        return try {
            if(res.isSuccessful){
                Result.success(res.body()?.data?.message ?: "Null Msg")
            } else {
                throw Exception("addMenuToCart Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}