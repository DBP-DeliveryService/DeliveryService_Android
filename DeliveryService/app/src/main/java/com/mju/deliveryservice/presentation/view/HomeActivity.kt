package com.mju.deliveryservice.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.ActivityHomeBinding
import com.mju.deliveryservice.presentation.view.home.HomeFragment

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavi.itemIconTintList = null
        binding.bottomNavi.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED

        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navi_home -> {
                    CustomLogger.d("home")
                    replaceFragment(HomeFragment())
                }

                R.id.navi_cart -> {
                    CustomLogger.d("cart")
                    replaceFragment(HomeFragment())
                }

                R.id.navi_profile -> {
                    CustomLogger.d("profile")
                    replaceFragment(HomeFragment())
                }
            }
            true
        }

        binding.bottomNavi.selectedItemId = R.id.navi_home
    }

    // 바텀 네비를 가시 상태 변경
    fun setNaviVisible(p: Boolean){
        binding.bottomNavi.visibility = if(p) View.VISIBLE else View.GONE
    }

    // 스택 없이 프래그먼트 전환 (뒤로가기 시 스택이 있어야지만 그곳으로 가짐)
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_home, fragment).commit()
    }

    // 스택 있이 프래그먼트 전환
    fun replaceFragmentWithStack(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_home, fragment)
            .addToBackStack(null)
            .commit()
    }
}