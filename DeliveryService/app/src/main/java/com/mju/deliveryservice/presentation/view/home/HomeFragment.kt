package com.mju.deliveryservice.presentation.view.home

import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.FragmentHomeBinding
import com.mju.deliveryservice.domain.model.category.Category
import com.mju.deliveryservice.presentation.base.BaseFragment
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.HomeActivity
import com.mju.deliveryservice.presentation.view.search.SearchFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var upCategoryAdapter: UpCategoryAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        viewModel.fetchData()
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        upCategoryAdapter = UpCategoryAdapter(listOf()).apply {
            setCategoryClickListener(object : UpCategoryAdapter.OnCategoryClickListener{
                override fun onClick(categoryItem: Category) {
                   moveSearchFragment(categoryItem)
                }
            })
        }
        binding.rvUpCategory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upCategoryAdapter
            addItemDecoration(upCategoryAdapter.HorizontalSpaceItemDecoration(16))
        }

        categoryAdapter = CategoryAdapter(listOf()).apply {
            setCategoryClickListener(object : CategoryAdapter.OnCategoryClickListener{
                override fun onClick(categoryItem: Category) {
                    moveSearchFragment(categoryItem)
                }
            })
        }
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context, 5)
            adapter = categoryAdapter
            addItemDecoration(categoryAdapter.GridSpacingItemDecoration(5, 32, true))
        }
    }

    /*
        observer: LiveData의 값이 변경됨을 감지하고 코드 수행
        아래 코드) uiState의 값이 Success인지 Failure인지 체크해서
                    성공 시 Recyclerview에 데이터 추가
                    실패 시 Error Log
    */
    override fun observer() {
        super.observer()

        viewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Failure -> {
                    CustomLogger.e(it.message)
                }
                is UiState.Loading -> {}
                is UiState.Success -> {
                    categoryAdapter.setData(it.data.categoryResList)
                    upCategoryAdapter.setData(it.data.top5CategoryResList)
                }
            }
        }
    }

    private fun moveSearchFragment(categoryItem: Category){
        (requireActivity() as HomeActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_home, SearchFragment(categoryItem.id, categoryItem.categoryName))
            .addToBackStack(null)
            .commit()
    }
}