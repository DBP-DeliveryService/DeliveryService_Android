package com.mju.deliveryservice.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mju.deliveryservice.databinding.ActivityStoreDetailBinding
import com.mju.deliveryservice.data.model.menu.MenuAdapter
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.model.menu.Menu



class StoreDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoreDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        // 타이틀 제거
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // 뒤로가기 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 홈 버튼 비활성화
        supportActionBar?.setDisplayShowHomeEnabled(false)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Setup RecyclerView
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.menuRecyclerView.adapter = MenuAdapter(getMenuList())
    }

    private fun getMenuList(): List<Menu> {
        // Replace with your actual data fetching logic
        return listOf(
            Menu("매콤달콤 파인애플족", "신선한 상추, 매콤 마늘소스", "34,000원", R.drawable.image_chicken),
            // Add more menu items here
        )
    }
}