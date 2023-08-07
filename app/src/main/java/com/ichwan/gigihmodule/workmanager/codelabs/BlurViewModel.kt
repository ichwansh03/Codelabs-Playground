package com.ichwan.gigihmodule.workmanager.codelabs

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.workmanager.codelabs.workers.BlurWorker
import com.ichwan.gigihmodule.workmanager.codelabs.workers.CleanupWorker
import com.ichwan.gigihmodule.workmanager.codelabs.workers.SaveImageToFileWorker

/**
 * This view model stores all of the data needed to display the BlurActivity.
 * It will also be the class where you start the background work using WorkManager.
 * */

class BlurViewModel(application: Application) : ViewModel() {

    private var imageUri: Uri? = null
    var outputUri: Uri? = null
    private val workManager = WorkManager.getInstance(application)
    internal val outputWorkInfos: LiveData<List<WorkInfo>> = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)

    init {
        imageUri = getImageUri(application.applicationContext)
    }

    internal fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }

    private fun createInputDataForUri() : Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    internal fun applyBlur(blurLevel: Int) {

        //one time request
        //workManager.enqueue(OneTimeWorkRequest.from(BlurWorker::class.java))

        //add WorkRequest to Cleanup temporary images
        var continuation = workManager
            .beginWith(OneTimeWorkRequest.Companion.from(CleanupWorker::class.java))

        //add WorkRequest to blur the image
        val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(createInputDataForUri())
            .build()

        continuation = continuation.then(blurRequest)

        //add WorkRequest to save the image to the filesystem
        val save = OneTimeWorkRequest.Builder(SaveImageToFileWorker::class.java).build()

        continuation = continuation.then(save)

        //actually start the work
        continuation.enqueue()


    }

    private fun uriOrNull(uriString: String?): Uri? {
        return if (!uriString.isNullOrEmpty()) {
            Uri.parse(uriString)
        } else {
            null
        }
    }

    private fun getImageUri(context: Context): Uri {
        val resources = context.resources

        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.android_cupcake))
            .appendPath(resources.getResourceTypeName(R.drawable.android_cupcake))
            .appendPath(resources.getResourceEntryName(R.drawable.android_cupcake))
            .build()
    }

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = uriOrNull(outputImageUri)
    }
}

class BlurViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BlurViewModel::class.java)) {
            BlurViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}