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


    private var user: MyPageInfo? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
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

        viewModel.getMyPageInfo(1){uiState ->  getProfileInfo(uiState)}

        binding.nickname.text = user?.nickname
        binding.name.text = user?.name
        binding.email.text = user?.email
        binding.address.text = user?.address
        binding.phoneNum.text = user?.phoneNum
        // 일단 기본 이미지 설정
        binding.profileImage.setImageResource(R.drawable.ic_profile_activate)

        //mypage 내용 변경
        binding.editNickname.setOnClickListener {


        }

        binding.editName.setOnClickListener {


        }

        binding.editEmail.setOnClickListener {


        }

        binding.editAddress.setOnClickListener {

        }

        binding.editPhoneNum.setOnClickListener {

        }

        binding.logout.setOnClickListener {

        }

        binding.deleteAccount.setOnClickListener {

        }


    }

    fun getProfileInfo(uiState: UiState<MyPageInfo>) {
        if (uiState is UiState.Success) {
            user = uiState.data
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}