package com.mju.deliveryservice.presentation.view.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.StoreRepositoryImpl
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch

class StoreDetailViewModel : ViewModel() {
    private val repositoryImpl = StoreRepositoryImpl()

    private val _uiState = MutableLiveData<UiState<StoreDetail>>(UiState.Loading)
    val uiState get() = _uiState

    var store: StoreDetail? = null

    fun getStoreDetail(storeId: Int, callback: (UiState<StoreDetail>) -> Unit) {

        callback(UiState.Loading)
        viewModelScope.launch {
            repositoryImpl.getStoreDetail(storeId)
                .onSuccess {
                    store = it
                    callback(UiState.Success(it))
                }
                .onFailure {
                    callback(UiState.Failure(it.message.toString()))
                }
        }
    }
}