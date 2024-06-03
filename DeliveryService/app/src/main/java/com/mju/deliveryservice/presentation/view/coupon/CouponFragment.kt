package com.mju.deliveryservice.presentation.view.coupon

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.FragmentCouponBinding
import com.mju.deliveryservice.domain.model.coupon.CouponEntity
import com.mju.deliveryservice.presentation.base.BaseFragment
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.HomeActivity
import com.mju.deliveryservice.presentation.view.MainHomeViewModel

class CouponFragment: BaseFragment<FragmentCouponBinding>(R.layout.fragment_coupon) {
    private val viewModel: CouponViewModel by viewModels()
    private val mainViewModel: MainHomeViewModel by activityViewModels()
    private lateinit var couponAdapter: CouponRvAdapter

    override fun initView() {
        (requireActivity() as HomeActivity).setNaviVisible(false)
        setRv()
        viewModel.fetchData()
    }

    override fun initListener() {
        super.initListener()

        with(binding){
            ibClear.setOnClickListener {
                mainViewModel.selectedCoupon = null
                requireActivity().supportFragmentManager.popBackStack()
            }
            btnIssue.setOnClickListener { viewModel.couponIssuance(etCouponCode.text.toString()) }
            btnRegister.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun observer() {
        super.observer()

        viewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {}
                is UiState.Failure -> {
                    showToast("쿠폰 조회 실패: ${it.message}")
                }
                is UiState.Success -> {
                    couponAdapter.setData(it.data)
                }
            }
        }

        viewModel.issuanceState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {}
                is UiState.Failure -> {
                    showToast("쿠폰 발급 실패: ${it.message}")
                }
                is UiState.Success -> {
                    viewModel.fetchData()
                    binding.etCouponCode.text = null
                }
            }
        }
    }

    private fun setRv(){
        couponAdapter = CouponRvAdapter(listOf()).apply {
            setCouponClickListener(object : CouponRvAdapter.OnCouponClickListener{
                override fun onClick(item: CouponEntity) {
                    mainViewModel.selectedCoupon = item
                }
            })
        }
        binding.rvCoupon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCoupon.adapter = couponAdapter
    }
}