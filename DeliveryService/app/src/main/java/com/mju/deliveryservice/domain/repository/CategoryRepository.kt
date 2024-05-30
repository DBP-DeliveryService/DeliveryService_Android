package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.category.SearchStoresByCategoryDTO
import com.mju.deliveryservice.domain.model.category.Categories
import com.mju.deliveryservice.domain.model.category.StoresByCategory

interface CategoryRepository {
    suspend fun getCategories(): Result<Categories>

    suspend fun getSearchStoresByCategory(): Result<List<StoresByCategory>>

}