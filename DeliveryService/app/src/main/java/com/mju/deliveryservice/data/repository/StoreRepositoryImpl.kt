package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.StoreService
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.domain.repository.StoreRepository

class StoreRepositoryImpl: StoreRepository {
    private val service = RetrofitClient.getInstance().create(StoreService::class.java)

    override suspend fun getStoreDetail(storeId: Int): Result<StoreDetail> {
        val res = service.getStoreDetail(storeId)

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.run { StoreDetail(deliveryTip, menuList, minPrice, rating, storeName) }
                Result.success(data)
            } else {
                throw Exception("getStoreDetail Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getMenuDetail(menuId: Int): Result<MenuDetail> {
        val res = service.getMenuDetail(menuId)

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.run { MenuDetail(menuContent, menuName, menuPictureUrl, price) }
                Result.success(data)
            } else {
                throw Exception("getMenuDetail Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}