package com.mju.deliveryservice.presentation.view.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.MyPageRepositoryImpl
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repositoryImpl = MyPageRepositoryImpl()

    private val _uiState = MutableLiveData<UiState<MyPageInfo>>(UiState.Loading)
    val uiState get() = _uiState

    fun getMyPageInfo(userId: Int, callback: (UiState<MyPageInfo>) -> Unit) {

        callback(UiState.Loading)
        viewModelScope.launch {
            repositoryImpl.getMyPage(userId)
                .onSuccess {
                    callback(UiState.Success(it))
                }
                .onFailure {
                    callback(UiState.Failure(it.message.toString()))
                }
        }
    }

}