package com.mju.deliveryservice.presentation.view.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.deliveryservice.databinding.FragmentStoreDetailBinding
import com.mju.deliveryservice.domain.model.category.StoresByCategory
import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.HomeActivity

class StoreDetailFragment(private val storeData: StoresByCategory) : Fragment() {
    private var _binding: FragmentStoreDetailBinding? = null
    private val binding get() = _binding!!

    private val storeDetailViewModel: StoreDetailViewModel by viewModels()

    private lateinit var menuAdapter: MenuAdapter

    var menuDetailList: List<MenuDetail> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as HomeActivity).setNaviVisible(false)

        binding.ibBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        val storeId =
            when (storeData.storeName) {
                "연희보리밥" -> 1
                "어글리김밥" -> 2
                else -> 1
            }
        storeDetailViewModel.getStoreDetail(storeId) { uiState -> // id=1 연희보리밥
            if (uiState is UiState.Success) {
                binding.storeName.text = uiState.data.storeName
                binding.storeMinPrice.text = "최소주문금액 ${uiState.data.minPrice}원"
                binding.storeRating.rating = uiState.data.rating.toFloat()
                binding.storeDeliveryTip.text = "배달팁 ${uiState.data.deliveryTip}원"
                menuDetailList = uiState.data.menuList
                binding.storeDeliveryTime.text = "최소 ${storeData.minDeliveryTime}분 소요 예정"

                setupRecyclerView()
            }
        }
    }

    private fun setupRecyclerView() {
        menuAdapter = MenuAdapter(menuDetailList).apply {
            setMenuClickListener(object : MenuAdapter.OnMenuClickListener{
                override fun onClick(pos: Int) {
                    moveMenuDetail(pos)
                }
            })
        }
        binding.menuRecyclerView.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun moveMenuDetail(pos: Int){
        (requireActivity() as HomeActivity).replaceFragmentWithStack(MenuOptionFragment(pos + 1))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}