package com.mju.deliveryservice.presentation.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.CategoryRepositoryImpl
import com.mju.deliveryservice.domain.model.category.Categories
import com.mju.deliveryservice.domain.model.category.Category
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    // 필요한 RepositoryImpl 객체 생성
    private val repositoryImpl = CategoryRepositoryImpl()

    // 상태 체크를 위한 LiveData 추가
    // UiState<T> -> Fragment or Activity에서 사용할 데이터 타입 넣으시면 됩니다.
    // Default 는 Unit
    private var _uiState = MutableLiveData<UiState<Categories>>(UiState.Loading)
    val uiState get() = _uiState

    // 호출 진행 전 상태를 로딩 상태로 놓고 API 호출 진행
    // onSuccess 와 onFailure 로 성공 여부에 따른 상태 변경 진행
    // 여기서 변경된 상태는 Activity or Fragment의 observe 아래 코드 진행
    fun fetchData(){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            repositoryImpl.getCategories()
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }
                .onFailure {
                    _uiState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}