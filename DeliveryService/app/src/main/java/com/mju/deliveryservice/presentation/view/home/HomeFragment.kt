package com.mju.deliveryservice.presentation.view.home

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.deliveryservice.R
import com.mju.deliveryservice.databinding.FragmentHomeBinding
import com.mju.deliveryservice.domain.model.CategoryItem
import com.mju.deliveryservice.domain.model.UpCategoryItem
import com.mju.deliveryservice.presentation.base.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var upCategoryAdapter: UpCategoryAdapter

    override fun initView() {
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        val upCategoryAdapter = UpCategoryAdapter(getUpCategoryItems())
        binding.rvUpCategory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upCategoryAdapter
            addItemDecoration(upCategoryAdapter.HorizontalSpaceItemDecoration(16))
        }

        val categoryAdapter = CategoryAdapter(getCategoryItems())
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context, 5)
            adapter = categoryAdapter
            addItemDecoration(categoryAdapter.GridSpacingItemDecoration(5, 32, true))
        }
    }

    private fun getUpCategoryItems(): List<UpCategoryItem> {
        return listOf(
            UpCategoryItem("브런치", R.drawable.image_brunch),
            UpCategoryItem("일식", R.drawable.image_japanese),
            UpCategoryItem("피자", R.drawable.image_pizza),
            UpCategoryItem("치킨", R.drawable.image_chicken)
        )
    }

    private fun getCategoryItems(): List<CategoryItem> {
        return listOf(
            CategoryItem("도시락"),
            CategoryItem("분식"),
            CategoryItem("디저트"),
            CategoryItem("치킨", ),
            CategoryItem("피자"),
            CategoryItem("한식"),
            CategoryItem("양식"),
            CategoryItem("일식"),
            CategoryItem("중식"),
            CategoryItem("야식"),
            CategoryItem("족발"),
            CategoryItem("보쌈"),
            CategoryItem("찜/탕"),
            CategoryItem("한식"),
            CategoryItem("한식"),
            CategoryItem("한식")
        )
    }
}