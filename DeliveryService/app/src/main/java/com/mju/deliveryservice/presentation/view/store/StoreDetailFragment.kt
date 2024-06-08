package com.mju.deliveryservice.presentation.view.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.deliveryservice.R
import com.mju.deliveryservice.databinding.FragmentStoreDetailBinding

import com.mju.deliveryservice.domain.model.store.MenuDetail
import com.mju.deliveryservice.domain.model.store.StoreDetail
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.cart.CartFragment

class StoreDetailFragment : Fragment() {
    private var _binding: FragmentStoreDetailBinding? = null
    private val binding get() = _binding!!

    private val menuViewModel: MenuViewModel by viewModels()
    private val storeDetailViewModel: StoreDetailViewModel by viewModels()

    private lateinit var menuAdapter: MenuAdapter

    var menuDetailList: ArrayList<MenuDetail> = arrayListOf()

    var menuList: ArrayList<Int> = arrayListOf(
        1, 2, 3, 4
    )

    var store: StoreDetail? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 네비게이션 바 설정 - 뒤로 가기
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
//            startActivity(Intent(requireContext(), StoreDetailFragment::class.java))
//            activity?.finish()
        }



        binding.orderButton.setOnClickListener {
            val bundle = Bundle()
            val cartFragment = CartFragment()
            cartFragment.arguments = bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_home, cartFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        storeDetailViewModel.getStoreDetail(1) { uiState ->
            if (uiState is UiState.Success) {
                store = uiState.data

                binding.storeName.text = store!!.storeName
                binding.storeMinPrice.text = "최소주문금액 ${store!!.minPrice}원"
                binding.storeRating.rating = store!!.rating.toFloat()
                binding.storeDeliveryTip.text = "배달팁 ${store!!.deliveryTip}원"
                // store의 menulist 받아오기 - DTO...??
                menuDetailList = store!!.menuList

            }
        }



        for(menuId in menuList) {
            menuViewModel.getMenu(menuId) { uiState ->
                getMenuList(menuId, uiState)
            }

        }


        setupRecyclerView()


    }

    fun getMenuList(menuId: Int, uiState: UiState<MenuDetail>) {
        if (uiState is UiState.Success) {
            menuDetailList.add(uiState.data)
        }
    }

    private fun setupRecyclerView() {
        // RecyclerView 어댑터와 레이아웃 매니저 설정
        menuAdapter = MenuAdapter(menuDetailList)
        binding.menuRecyclerView.apply {
            menuAdapter = menuAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}