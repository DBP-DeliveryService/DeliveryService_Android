package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail

interface StoreRepository {
    suspend fun getStoreDetail(storeId: Int): Result<StoreDetail>

    suspend fun getMenuDetail(menuId: Int): Result<MenuDetail>
}