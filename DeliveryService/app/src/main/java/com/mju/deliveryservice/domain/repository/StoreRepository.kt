package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.store.MenuDetailDTO
import com.mju.deliveryservice.data.model.store.StoreDetailDTO
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreRepository {
    suspend fun getStoreDetail(storeId: Int): Result<StoreDetail>

    suspend fun getMenuDetail(menuId: Int): Result<MenuDetail>
}