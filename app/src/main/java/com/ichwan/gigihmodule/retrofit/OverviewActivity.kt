package com.ichwan.gigihmodule.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.ActivityOverviewBinding

class OverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}