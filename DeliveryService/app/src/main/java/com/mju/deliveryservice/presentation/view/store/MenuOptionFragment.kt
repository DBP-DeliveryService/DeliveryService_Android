package com.mju.deliveryservice.presentation.view.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.mju.deliveryservice.databinding.FragmentMenuOptionBinding
import com.mju.deliveryservice.domain.model.store.MenuDetail

class MenuOptionFragment : Fragment(){
    private var _binding: FragmentMenuOptionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModels()

    private var menu: MenuDetail? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuOptionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu = viewModel.getMenuOption()

        Glide.with(this).load(menu?.menuPictureUrl).into(binding.storeImage)
        binding.menuName.text = menu?.menuName
        binding.menuDescription.text = menu?.menuContent
        binding.menuPrice.text = "${menu?.price}원"


    }
}