package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.CategoryService
import com.mju.deliveryservice.data.remote.service.MyPageService
import com.mju.deliveryservice.data.remote.service.StoreService
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.domain.model.category.Categories
import com.mju.deliveryservice.domain.model.category.Category
import com.mju.deliveryservice.domain.model.category.StoresByCategory
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.domain.repository.CategoryRepository
import com.mju.deliveryservice.domain.repository.MyPageRepository
import com.mju.deliveryservice.domain.repository.StoreRepository

class CategoryRepositoryImpl: CategoryRepository {
    private val service = RetrofitClient.getInstance().create(CategoryService::class.java)

    override suspend fun getCategories(): Result<Categories> {
        val res = service.getCategories()

        return try {
            if(res.isSuccessful){
                val categoryResList = res.body()!!.data.categoryResList.map { Category(categoryName = it.categoryName, categoryImgUrl = it.categoryImgUrl, id = it.id) }
                val top5CategoryResList = res.body()!!.data.top5CategoryResList.map { Category(categoryName = it.categoryName, categoryImgUrl = it.categoryImgUrl, id = it.id) }
                Result.success(Categories(categoryResList, top5CategoryResList))
            } else {
                throw Exception("getCategories Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getSearchStoresByCategory(): Result<List<StoresByCategory>> {
        val res = service.getSearchStoresByCategory()

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.map { StoresByCategory(
                    deliveryTip = it.deliveryTip,
                    detailCategoryName = it.detailCategoryName,
                    detailOpreateStatus = it.detailOpreateStatus,
                    minDeliveryTime = it.minDeliveryTime,
                    minPrice = it.minPrice,
                    rating = it.rating,
                    storeName = it.storeName
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