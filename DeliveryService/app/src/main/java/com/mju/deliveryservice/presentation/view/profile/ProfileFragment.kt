package com.mju.deliveryservice.presentation.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mju.deliveryservice.R
import com.mju.deliveryservice.databinding.FragmentProfileBinding
import com.mju.deliveryservice.domain.model.mypage.MyPageInfo
import com.mju.deliveryservice.presentation.utils.UiState
import androidx.fragment.app.viewModels


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMyPageInfo(1){uiState ->  getProfileInfo(uiState)}
        binding.profileImage.setImageResource(R.drawable.ic_profile_activate)
    }

    private fun getProfileInfo(uiState: UiState<MyPageInfo>) {
        if (uiState is UiState.Success) {
            val userInfo = uiState.data

            binding.nickname.text = userInfo.nickname
            binding.name.text = userInfo.name
            binding.email.text = userInfo.email
            binding.address.text = userInfo.address
            binding.phoneNum.text = userInfo.phoneNum
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}