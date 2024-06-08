package com.mju.deliveryservice.presentation.view.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.CartRepositoryImpl
import com.mju.deliveryservice.data.repository.StoreRepositoryImpl
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch
class MenuViewModel : ViewModel() {

    private val repositoryImpl = StoreRepositoryImpl()
    private val cartRepositoryImpl = CartRepositoryImpl()

    private val _uiState = MutableLiveData<UiState<MenuDetail>>(UiState.Loading)
    val uiState get() = _uiState

    private val _cartState = MutableLiveData<UiState<Unit>>(UiState.Loading)
    val cartState get() = _cartState

    fun getMenu(menuId: Int) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            repositoryImpl.getMenuDetail(menuId)
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }
                .onFailure {
                    _uiState.value = UiState.Failure(it.message.toString())
                }
        }
    }

    fun addToCart(menuId: Int, quantity: Int){
        _cartState.value = UiState.Loading

        viewModelScope.launch {
            cartRepositoryImpl.addMenuToCart(menuId, quantity)
                .onSuccess {
                    _cartState.value = UiState.Success(Unit)
                }
                .onFailure {
                    _cartState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}