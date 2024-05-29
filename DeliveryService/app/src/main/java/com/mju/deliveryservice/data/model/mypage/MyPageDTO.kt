package com.mju.deliveryservice.data.model.mypage
import com.google.gson.annotations.SerializedName

data class MyPageDTO(
    @SerializedName("address") val address: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("phoneNum") val phoneNum: String
)