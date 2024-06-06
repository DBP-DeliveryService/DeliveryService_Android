package com.mju.deliveryservice.presentation.view


import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.mju.deliveryservice.R
import com.mju.deliveryservice.databinding.ActivityMenuOptionBinding

import androidx.appcompat.widget.Toolbar


class MenuOptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, StoreDetailActivity::class.java))
            finish()
        }
    }
}

