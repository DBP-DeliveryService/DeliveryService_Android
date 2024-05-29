package com.mju.deliveryservice.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") val data: T,
    @SerializedName("description") val description: Any,
    @SerializedName("status") val status: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("transaction_time") val transactionTime: String
)
