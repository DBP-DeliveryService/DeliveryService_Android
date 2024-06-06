package com.mju.deliveryservice.presentation.view.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mju.deliveryservice.R
import com.mju.deliveryservice.databinding.FragmentCartBinding
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.order.OrderFragment

class CartFragment : Fragment() {

    // ViewBinding 객체 선언
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    // ViewModel 선언
    private val viewModel: CartViewModel by viewModels()

    // CartAdapter 선언
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰 바인딩 객체 초기화
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 설정
        setupRecyclerView()

        // 옵저버 설정
        setupObservers()

        // 리스너 설정
        setupListeners()

        // 장바구니 데이터 가져오기
        viewModel.fetchCart()
    }

    private fun setupRecyclerView() {
        // RecyclerView 어댑터와 레이아웃 매니저 설정
        cartAdapter = CartAdapter(
            onQuantityChange = { menuId, quantity -> viewModel.updateCart(menuId, quantity) },
            onDelete = { menuId -> viewModel.deleteCart(menuId) }
        )
        binding.rvCartList.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        // UI 상태를 관찰하여 RecyclerView에 데이터 설정
        viewModel.uiState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is UiState.Loading -> {
                    // 로딩 상태 처리 (예: ProgressBar 표시)
                }
                is UiState.Success -> {
                    // 성공적으로 데이터를 가져오면 어댑터에 데이터 설정
                    cartAdapter.setData(state.data)
                }
                is UiState.Failure -> {
                    // 실패 상태 처리 (예: 에러 메시지 표시)
                    Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupListeners() {
        // 주문하기 버튼 클릭 리스너 설정
        binding.btnOrder.setOnClickListener {
            // 주문하기 페이지로 이동
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_home, OrderFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰 바인딩 객체 해제
        _binding = null
    }
}
