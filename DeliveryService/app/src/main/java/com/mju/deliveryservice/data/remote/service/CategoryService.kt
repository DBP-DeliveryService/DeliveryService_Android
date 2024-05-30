package com.mju.deliveryservice.data.remote.service

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.category.CategoryDTO
import com.mju.deliveryservice.data.model.category.SearchStoresByCategoryDTO
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {
    @GET("/categories")
    suspend fun getCategories(): Response<BaseResponse<CategoryDTO>>

    @GET("/categories/{categoryId}")
    suspend fun getSearchStoresByCategory(): Response<BaseResponse<SearchStoresByCategoryDTO>>

}