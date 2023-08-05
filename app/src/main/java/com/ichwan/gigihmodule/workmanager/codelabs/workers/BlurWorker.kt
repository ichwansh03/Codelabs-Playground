package com.ichwan.gigihmodule.workmanager.codelabs.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.common.util.concurrent.ListenableFuture
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.workmanager.codelabs.KEY_IMAGE_URI
import java.lang.Thread.sleep

private const val TAG = "BlurWorker"
class BlurWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        createStatusNotification("Bluring image", applicationContext)

        return try {

            val picture = BitmapFactory.decodeResource(
                applicationContext.resources,
                R.drawable.android_cupcake)

            val output = blurBitmap(picture, applicationContext)

            val outputUri = writeBitmapToFile(applicationContext, output)

            createStatusNotification("Output is $outputUri", applicationContext)

            Result.success()
        } catch (throwable: Throwable) {
            Log.e(TAG, "Error applying blur")
            throwable.printStackTrace()
            Result.failure()
        }

    }
}
