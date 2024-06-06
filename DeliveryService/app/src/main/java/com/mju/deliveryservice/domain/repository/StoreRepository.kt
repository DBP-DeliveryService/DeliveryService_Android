package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.domain.model.category.StoresByCategory
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import retrofit2.http.Query

interface StoreRepository {
    suspend fun getStoreDetail(storeId: Int): Result<StoreDetail>

    suspend fun getMenuDetail(menuId: Int): Result<MenuDetail>

    suspend fun searchStore(
        @Query("storeName") storeName: String
    ): Result<StoresByCategory>
}