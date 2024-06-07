package com.mju.deliveryservice.presentation.view

import androidx.lifecycle.ViewModel
import com.mju.deliveryservice.domain.model.coupon.CouponEntity

class MainHomeViewModel: ViewModel() {
    var selectedCoupon: CouponEntity? = null
}