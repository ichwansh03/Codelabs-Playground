package com.ichwan.gigihmodule.workmanager.codelabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ichwan.gigihmodule.databinding.ActivityWorkerBinding

class WorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}