package com.mju.deliveryservice.presentation.view.profile


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mju.deliveryservice.R
import androidx.appcompat.widget.Toolbar
import com.mju.deliveryservice.data.model.mypage.MyPageDTO
import com.mju.deliveryservice.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar 설정
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // 더미 데이터
        val myPageDTO = MyPageDTO(
            address = "서울특별시 강남구",
            email = "user@example.com",
            name = "김명지",
            nickname = "ㅇㅇ",
            phoneNum = "010-1234-5678"
        )

        // 뷰에 데이터 바인딩
        binding.nickname.text = myPageDTO.nickname
        binding.name.text = myPageDTO.name
        binding.email.text = myPageDTO.email
        binding.address.text = myPageDTO.address
        binding.phoneNum.text = myPageDTO.phoneNum
        binding.profileImage.setImageResource(R.drawable.ic_profile_activate)

//        // 프로필 이미지 클릭 처리
//        binding.profileImage.setOnClickListener {
//            // 프로필 이미지 변경 로직
//        }
//
//        // 편집 버튼 클릭 처리
//        binding.editNickname.setOnClickListener {
//            startActivity(Intent(this, EditNicknameActivity::class.java))
//        }
//
//        binding.editName.setOnClickListener {
//            startActivity(Intent(this, EditNameActivity::class.java))
//        }
//
//        binding.editEmail.setOnClickListener {
//            startActivity(Intent(this, EditEmailActivity::class.java))
//        }
//
//        binding.editPhoneNum.setOnClickListener {
//            startActivity(Intent(this, EditPhoneActivity::class.java))
//        }


//        // 로그아웃 클릭 처리
//        binding.logout.setOnClickListener {
//            val logoutIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://example.com/logout"))
//            startActivity(logoutIntent)
//        }
//
//        // 계정 삭제 클릭 처리
//        binding.deleteAccount.setOnClickListener {
//            val deleteAccountIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://example.com/delete_account"))
//            startActivity(deleteAccountIntent)
//        }
    }


}