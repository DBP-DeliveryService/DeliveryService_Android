package com.mju.deliveryservice.presentation.view.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.StoreRepositoryImpl
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch
class MenuViewModel : ViewModel() {

    private val repositoryImpl = StoreRepositoryImpl()

    private val _uiState = MutableLiveData<UiState<MenuDetail>>(UiState.Loading)
    val uiState get() = _uiState
    var menu: MenuDetail? = null
    fun getMenu(menuId: Int, callback: (UiState<MenuDetail>) -> Unit) {

        callback(UiState.Loading)
        viewModelScope.launch {
            repositoryImpl.getMenuDetail(menuId)
                .onSuccess {
                    menu = it
                    callback(UiState.Success(it))
                }
                .onFailure {
                    callback(UiState.Failure(it.message.toString()))
                }
        }
    }

    fun getMenuOption(): MenuDetail? {
        return menu
    }
}   // 메뉴 리스트 가져오기