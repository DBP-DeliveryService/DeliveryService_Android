package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.StoreService
import com.mju.deliveryservice.domain.model.category.StoresByCategory
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.domain.repository.StoreRepository

class StoreRepositoryImpl: StoreRepository {
    private val service = RetrofitClient.getInstance().create(StoreService::class.java)

    override suspend fun getStoreDetail(storeId: Int): Result<StoreDetail> {
        val res = service.getStoreDetail(storeId)

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.run { StoreDetail(
                    deliveryTip,
                    menuList.map {
                        MenuDetail(
                            menuContent = it.menuContent, menuName = it.menuName, menuPictureUrl = it.menuPictureUrl, price = it.price
                        ) },
                    minPrice,
                    rating,
                    storeName)
                }
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

    override suspend fun searchStore(storeName: String): Result<StoresByCategory> {
        val res = service.searchStore(storeName)

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.run { StoresByCategory(
                    deliveryTip = this.deliveryTip,
                    detailCategoryName = this.detailCategoryName,
                    detailOpreateStatus = this.detailOpreateStatus,
                    minDeliveryTime = this.minDeliveryTime,
                    minPrice = this.minPrice,
                    rating = this.rating,
                    storeName = this.storeName
                ) }
                Result.success(data)
            } else {
                throw Exception("getSearchStoresByCategory Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}