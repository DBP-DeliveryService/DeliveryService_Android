package com.mju.deliveryservice.presentation.view.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.CartRepositoryImpl
import com.mju.deliveryservice.domain.model.CartItem
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val repositoryImpl = CartRepositoryImpl()
    private val _uiState = MutableLiveData<UiState<List<CartItem>>>(UiState.Loading)
    val uiState get() = _uiState

    // 장바구니 데이터 가져오기
    fun fetchCart() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repositoryImpl.getCart()
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }
                .onFailure {
                    _uiState.value = UiState.Failure(it.message.toString())
                }
        }
    }

    // 메뉴를 장바구니에 추가
    fun addMenuToCart(menuId: Int, quantity: Int) {
        viewModelScope.launch {
            repositoryImpl.addMenuToCart(menuId, quantity)
                .onFailure {
                    _uiState.value = UiState.Failure(it.message.toString())
                }
        }
    }

    // 장바구니 수량 업데이트
    fun updateCart(menuId: Int, quantity: Int) {
        viewModelScope.launch {
            repositoryImpl.updateCart(menuId, quantity)
                .onSuccess {
                    fetchCart() // 데이터 갱신
                }
                .onFailure {
                }
        }
    }

    // 장바구니에서 메뉴 삭제
    fun deleteCart(menuId: Int) {
        viewModelScope.launch {
            repositoryImpl.deleteCart(menuId)
                .onSuccess {
                    fetchCart() // 데이터 갱신
                }
                .onFailure {
                }
        }
    }
}
