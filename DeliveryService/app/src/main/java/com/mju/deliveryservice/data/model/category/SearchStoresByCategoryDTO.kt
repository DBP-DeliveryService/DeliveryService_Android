package com.mju.deliveryservice.data.model.category
import com.google.gson.annotations.SerializedName


class SearchStoresByCategoryDTO : ArrayList<SearchStoresByCategoryDTOItem>()

data class SearchStoresByCategoryDTOItem(
    @SerializedName("deliveryTip") val deliveryTip: Int,
    @SerializedName("detailCategoryName") val detailCategoryName: String,
    @SerializedName("detailOpreateStatus") val detailOpreateStatus: String,
    @SerializedName("minDeliveryTime") val minDeliveryTime: Int,
    @SerializedName("minPrice") val minPrice: Int,
    @SerializedName("rating") val rating: Int,
    @SerializedName("storeName") val storeName: String
)