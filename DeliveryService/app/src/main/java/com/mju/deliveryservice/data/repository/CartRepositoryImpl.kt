package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.model.cart.AddMenuToCartRequestDTO
import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.CartService
import com.mju.deliveryservice.domain.model.CartItem
import com.mju.deliveryservice.domain.repository.CartRepository

class CartRepositoryImpl: CartRepository {
    private val service = RetrofitClient.getInstance().create(CartService::class.java)

    override suspend fun getCart(): Result<List<CartItem>> {
        val res = service.getCart()

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.map { CartItem(menuId = it.menuId, menuContent = it.menuContent, menuName = it.menuName, price = it.price, quantity = it.quantity) }

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

    override suspend fun deleteCart(menuId: Int): Result<String> {
        val res = service.deleteCart(menuId)

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
