package com.mju.deliveryservice.presentation.view.search

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.FragmentSearchBinding
import com.mju.deliveryservice.presentation.base.BaseFragment
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.HomeActivity

class SearchFragment(private val categoryId: Int, private val categoryName: String): BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private lateinit var searchStoreAdapter: SearchResultAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun initView() {
        binding.tvCategoryName.text = categoryName
        setAdapter()
        viewModel.fetchData(categoryId)
    }

    override fun initListener() {
        super.initListener()
        with(binding){
            ibBack.setOnClickListener { (requireActivity() as HomeActivity).supportFragmentManager.popBackStack() }

        }
    }

    private fun setAdapter(){
        searchStoreAdapter = SearchResultAdapter(listOf())
        binding.rvStoreList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStoreList.adapter = searchStoreAdapter
    }

    override fun observer() {
        super.observer()

        viewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Failure -> {
                    CustomLogger.e(it.message)
                }
                is UiState.Loading -> {}
                is UiState.Success -> {
                    searchStoreAdapter.setData(it.data)
                }
            }
        }
    }
}