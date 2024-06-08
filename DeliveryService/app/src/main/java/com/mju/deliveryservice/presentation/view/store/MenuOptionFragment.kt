package com.mju.deliveryservice.presentation.view.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.mju.deliveryservice.databinding.FragmentMenuOptionBinding
import com.mju.deliveryservice.presentation.utils.UiState
import com.mju.deliveryservice.presentation.view.HomeActivity

class MenuOptionFragment(private val menuId: Int) : Fragment(){
    private var _binding: FragmentMenuOptionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuOptionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMenu(menuId)
        observer()
        initListener()
    }

    private fun observer(){
        with(viewModel){
            uiState.observe(viewLifecycleOwner){
                when(it){
                    is UiState.Failure -> {}
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        Glide.with(requireContext()).load(it.data.menuPictureUrl).into(binding.menuImage)
                        binding.menuName.text = it.data.menuName
                        binding.menuDescription.text = it.data.menuContent
                        binding.menuPrice.text = "${it.data.price}원"
                    }
                }
            }

            cartState.observe(viewLifecycleOwner){
                when(it){
                    is UiState.Failure -> {}
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        (requireActivity() as HomeActivity).setNaviItem(1)
                    }
                }
            }
        }
    }

    private fun initListener(){
        binding.ibBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.addToCartButton.setOnClickListener {
            viewModel.addToCart(menuId, binding.tvQuantity.text.toString().toInt())
        }

        binding.btnPlus.setOnClickListener {
            binding.tvQuantity.text = (binding.tvQuantity.text.toString().toInt() + 1).toString()
        }

        binding.btnMinus.setOnClickListener {
            if(binding.tvQuantity.text.toString().toInt() > 1){
                binding.tvQuantity.text = (binding.tvQuantity.text.toString().toInt() - 1).toString()
            }
        }
    }
}