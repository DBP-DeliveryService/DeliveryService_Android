package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.MyPageService
import com.mju.deliveryservice.data.remote.service.StoreService
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.domain.repository.MyPageRepository
import com.mju.deliveryservice.domain.repository.StoreRepository

class MyPageRepositoryImpl: MyPageRepository {
    private val service = RetrofitClient.getInstance().create(MyPageService::class.java)

    override suspend fun getMyPage(userId: Int): Result<MyPageInfo> {
        val res = service.getMyPage(userId)

        return try {
            if(res.isSuccessful){
                val data = res.body()!!.data.run { MyPageInfo(address, email, name, nickname, phoneNum) }
                Result.success(data)
            } else {
                throw Exception("getMyPage Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}