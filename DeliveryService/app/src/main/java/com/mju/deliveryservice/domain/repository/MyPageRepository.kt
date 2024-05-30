package com.mju.deliveryservice.domain.repository

import com.mju.deliveryservice.domain.model.mypage.MyPageInfo

interface MyPageRepository {
    suspend fun getMyPage(userId: Int): Result<MyPageInfo>
}