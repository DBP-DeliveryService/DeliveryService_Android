package com.mju.deliveryservice.data.model.category
import com.google.gson.annotations.SerializedName


data class CategoryDTO(
    @SerializedName("categoryResList") val categoryResList: List<CategoryResDTO>,
    @SerializedName("top5CategoryResList") val top5CategoryResList: List<CategoryResDTO>
)

data class CategoryResDTO(
    @SerializedName("categoryImgUrl") val categoryImgUrl: String,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("id") val id: Int
)