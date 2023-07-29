package com.ichwan.gigihmodule.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.ActivityModelBinding

class ModelActivity : AppCompatActivity() {

    private lateinit var bind: ActivityModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityModelBinding.inflate(layoutInflater)
        setContentView(bind.root)


    }
}