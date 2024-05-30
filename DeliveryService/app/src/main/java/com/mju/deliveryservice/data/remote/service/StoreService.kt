package com.mju.deliveryservice.data.remote.service

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.store.MenuDetailDTO
import com.mju.deliveryservice.data.model.store.StoreDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreService {
    @GET("/stores/{storeId}")
    suspend fun getStoreDetail(
        @Path("storeId") storeId: Int
    ): Response<BaseResponse<StoreDetailDTO>>

    @GET("/stores/menu/{menuId}")
    suspend fun getMenuDetail(
        @Path("menuId") menuId: Int
    ): Response<BaseResponse<MenuDetailDTO>>
}