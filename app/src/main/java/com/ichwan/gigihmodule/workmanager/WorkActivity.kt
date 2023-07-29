package com.ichwan.gigihmodule.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.ActivityWorkBinding
import java.util.concurrent.TimeUnit

class WorkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkBinding
    private lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = Data.Builder()
            .putString(WorkerImpl.key, "Worker")
            .build()

        val workRequest = PeriodicWorkRequest.Builder(WorkerImpl::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraint)
            //.setInputData(workDataOf(WorkerImpl.key to "worker"))
            .setInputData(data)
            .build()

        workManager = WorkManager.getInstance(this)
        workManager.enqueue(workRequest)

    }
}