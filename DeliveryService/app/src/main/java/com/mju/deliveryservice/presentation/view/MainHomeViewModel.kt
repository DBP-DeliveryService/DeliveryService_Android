package com.mju.deliveryservice.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mju.deliveryservice.domain.model.coupon.CouponEntity

class MainHomeViewModel: ViewModel() {
    private val _selectedCoupon = MutableLiveData<CouponEntity?>()
    val selectedCoupon: LiveData<CouponEntity?> get() = _selectedCoupon

    fun setSelectedCoupon(coupon: CouponEntity?) {
        _selectedCoupon.value = coupon
    }
}
