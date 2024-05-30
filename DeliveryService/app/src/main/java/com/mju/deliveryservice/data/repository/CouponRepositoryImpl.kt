package com.mju.deliveryservice.data.repository

import com.mju.deliveryservice.data.model.coupon.CouponIssuanceRequestDTO
import com.mju.deliveryservice.data.remote.RetrofitClient
import com.mju.deliveryservice.data.remote.service.CouponService
import com.mju.deliveryservice.data.remote.service.MyPageService
import com.mju.deliveryservice.data.remote.service.StoreService
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.domain.repository.CouponRepository
import com.mju.deliveryservice.domain.repository.MyPageRepository
import com.mju.deliveryservice.domain.repository.StoreRepository

class CouponRepositoryImpl: CouponRepository {
    private val service = RetrofitClient.getInstance().create(CouponService::class.java)

    override suspend fun couponIssuance(couponCode: String): Result<String> {
        val res = service.couponIssuance(CouponIssuanceRequestDTO(couponCode))

        return try {
            if(res.isSuccessful){
                Result.success(res.body()?.data?.message ?: "Null Msg")
            } else {
                throw Exception("getMyPage Fail")
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}