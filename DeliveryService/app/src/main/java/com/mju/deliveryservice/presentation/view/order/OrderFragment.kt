package com.mju.deliveryservice.presentation.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.model.mypage.MyPageDTO
import com.mju.deliveryservice.databinding.FragmentOrderBinding
import com.mju.deliveryservice.presentation.view.HomeActivity
import com.mju.deliveryservice.presentation.view.MainHomeViewModel
import com.mju.deliveryservice.presentation.view.coupon.CouponFragment

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainHomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as HomeActivity).setNaviVisible(false)

        setupSpinners()
        setupListeners()

        val totalAmount = arguments?.getInt("totalAmount", 0) ?: 0

        // 더미 데이터
        val myPageDTO = MyPageDTO(
            address = "서울특별시 강남구",
            email = "user@example.com",
            name = "김명지",
            nickname = "ㅇㅇ",
            phoneNum = "010-1234-5678"
        )

        // 뷰에 데이터 바인딩
        binding.tvDeliveryAddress.text = myPageDTO.address
        binding.tvUserPhoneNumber.text = myPageDTO.phoneNum

        binding.tvTotalPrice.text = totalAmount.toString()


        // MainHomeViewModel을 통해 선택한 쿠폰의 정보 가져오기
        val selectedCoupon = mainViewModel.selectedCoupon.value
        // 선택한 쿠폰의 정보를 사용하여 필요한 작업 수행
        if (selectedCoupon != null) {
            val discountedTotalAmount = totalAmount - selectedCoupon.discountAmount
            binding.tvDiscount.text = "-${selectedCoupon.discountAmount}원"
            binding.tvTotalPrice.text = discountedTotalAmount.toString()
        }

    }

    private fun setupSpinners() {
        // 라이더 스피너에 데이터 설정
        val riderOptions = listOf("문 앞에 두고 벨 눌러주세요", "문 앞에 두고 노크해주세요", "문 앞에 두면 가져갈게요", "직접 받을게요")
        val riderAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, riderOptions)
        riderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spRider.adapter = riderAdapter

        // 결제수단 스피너에 데이터 설정
        val paymentMethods = listOf("신용카드", "휴대폰 결제", "카카오페이", "네이버페이", "만나서 카드결제", "만나서 현금결제")
        val paymentAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, paymentMethods)
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spPaymentMethod.adapter = paymentAdapter
    }

    private fun setupListeners() {
        binding.ibCoupon.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_home, CouponFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.btnPurchase.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_home, OrderCompleteFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

