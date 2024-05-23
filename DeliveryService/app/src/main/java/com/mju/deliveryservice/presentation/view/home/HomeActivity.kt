package com.mju.deliveryservice.presentation.view.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // View Setting
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
    }

    fun setNaviVisible(p: Boolean){
        binding.bottomNavi.visibility = if(p) View.VISIBLE else View.GONE
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_home, fragment).commit()
    }

    fun replaceFragmentWithStack(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_home, fragment)
            .addToBackStack(null)
            .commit()
    }
}