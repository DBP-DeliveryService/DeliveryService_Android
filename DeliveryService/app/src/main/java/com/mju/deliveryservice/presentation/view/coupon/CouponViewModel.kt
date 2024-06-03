package com.mju.deliveryservice.presentation.view.coupon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.CouponRepositoryImpl
import com.mju.deliveryservice.domain.model.coupon.CouponEntity
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch

class CouponViewModel: ViewModel() {

    private val repositoryImpl = CouponRepositoryImpl()

    private var _uiState = MutableLiveData<UiState<List<CouponEntity>>>(UiState.Loading)
    val uiState get() = _uiState

    private var _issuanceState = MutableLiveData<UiState<Unit>>(UiState.Loading)
    val issuanceState get() = _issuanceState

    fun fetchData(){
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repositoryImpl.getCoupons()
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }
                .onFailure {
                    _uiState.value = UiState.Failure(it.message.toString())
                }
        }
    }

    fun couponIssuance(couponCode: String){
        _issuanceState.value = UiState.Loading

        viewModelScope.launch {
            repositoryImpl.couponIssuance(couponCode)
                .onSuccess {
                    _issuanceState.value = UiState.Success(Unit)
                }
                .onFailure {
                    _issuanceState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}