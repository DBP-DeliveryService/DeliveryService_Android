package com.mju.deliveryservice.presentation.view.home

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.FragmentHomeBinding
import com.mju.deliveryservice.domain.model.category.Category
import com.mju.deliveryservice.domain.model.category.StoresByCategory
import com.mju.deliveryservice.presentation.base.BaseFragment
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.HomeActivity
import com.mju.deliveryservice.presentation.view.search.SearchFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var upCategoryAdapter: UpCategoryAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        (requireActivity() as HomeActivity).setNaviVisible(true)
        viewModel.fetchData()
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        upCategoryAdapter = UpCategoryAdapter(listOf()).apply {
            setCategoryClickListener(object : UpCategoryAdapter.OnCategoryClickListener{
                override fun onClick(categoryItem: Category) {
                   moveSearchFragment(categoryItem, null)
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
                    moveSearchFragment(categoryItem, null)
                }
            })
        }
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context, 5)
            adapter = categoryAdapter
            addItemDecoration(categoryAdapter.GridSpacingItemDecoration(5, 32, true))
        }

        binding.etSearch.setOnKeyListener(object : OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if((event?.action == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER){
                    if(binding.etSearch.text.isEmpty()){ showToast("검색어를 입력해주세요") }
                    else {
                        val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view?.windowToken, 0)

                        viewModel.searchStore(binding.etSearch.text.toString())
                    }
                    return true
                }
                return false
            }
        })
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

        viewModel.searchState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Failure -> {
                    CustomLogger.e(it.message)
                    showToast("해당 검색어에 맞는 가게를 찾을 수 없습니다")
                }
                is UiState.Loading -> {}
                is UiState.Success -> {
                    moveSearchFragment(null, it.data)
                }
            }
        }
    }

    private fun moveSearchFragment(categoryItem: Category?, searchResult: StoresByCategory?){
        (requireActivity() as HomeActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fl_home, SearchFragment(categoryItem?.id, categoryItem?.categoryName, if(searchResult == null) listOf() else listOf(searchResult)))
            .addToBackStack(null)
            .commit()
    }
}