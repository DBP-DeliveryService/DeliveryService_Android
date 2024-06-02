package com.mju.deliveryservice.presentation.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mju.deliveryservice.data.repository.CategoryRepositoryImpl
import com.mju.deliveryservice.domain.model.category.Categories
import com.mju.deliveryservice.domain.model.category.Category
import com.mju.deliveryservice.domain.model.category.StoresByCategory
import com.mju.deliveryservice.presentation.utils.UiState
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val repositoryImpl = CategoryRepositoryImpl()

    private var _uiState = MutableLiveData<UiState<List<StoresByCategory>>>(UiState.Loading)
    val uiState get() = _uiState

    fun fetchData(categoryId: Int){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            repositoryImpl.getSearchStoresByCategory(categoryId)
                .onSuccess {
                    _uiState.value = UiState.Success(it)
                }
                .onFailure {
                    _uiState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}