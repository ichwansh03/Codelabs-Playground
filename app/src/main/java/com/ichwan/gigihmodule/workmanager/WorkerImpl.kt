package com.ichwan.gigihmodule.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerImpl(context: Context, param: WorkerParameters): Worker(context, param) {

    companion object {
        const val key = "WORKER_KEY"
    }

    override fun doWork(): Result {
        return try {
            Log.d("Worker: ", inputData.getString(key).toString())
            Result.success()
        } catch (e: Exception){
            Result.failure()
        }
    }

}