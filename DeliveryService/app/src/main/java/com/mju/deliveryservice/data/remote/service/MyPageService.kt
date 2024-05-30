package com.mju.deliveryservice.data.remote.service

import com.mju.deliveryservice.data.model.BaseResponse
import com.mju.deliveryservice.data.model.mypage.MyPageDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageService {
    @GET("/mypage/{userId}")
    suspend fun getMyPage(
        @Path("userId") userId: Int
    ): Response<BaseResponse<MyPageDTO>>
}