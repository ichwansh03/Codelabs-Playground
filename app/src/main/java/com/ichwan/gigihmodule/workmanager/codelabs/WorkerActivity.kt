package com.ichwan.gigihmodule.workmanager.codelabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.ActivityWorkerBinding

class WorkerActivity : AppCompatActivity() {

    private val viewModel: BlurViewModel by viewModels { BlurViewModelFactory(application) }
    private lateinit var binding: ActivityWorkerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goButton.setOnClickListener { viewModel.applyBlur(blurLevel) }

        binding.seeFileButton.setOnClickListener {
            viewModel.outputUri?.let { currentUri ->
                val actionView = Intent(Intent.ACTION_VIEW, currentUri)
                actionView.resolveActivity(packageManager)?.run { 
                    startActivity(actionView)
                }
            }
        }
        
        binding.cancelButton.setOnClickListener { viewModel.cancelWork() }
        
        viewModel.outputWorkInfos.observe(this, workInfosObserver())
    }

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                if (!outputImageUri.isNullOrEmpty()) {
                    viewModel.setOutputUri(outputImageUri)
                    binding.seeFileButton.visibility = View.VISIBLE
                }
            } else {
                showWorkInProgress()
            }
        }
    }

    private fun showWorkInProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            cancelButton.visibility = View.VISIBLE
            goButton.visibility = View.GONE
            seeFileButton.visibility = View.GONE
        }
    }

    private fun showWorkFinished() {
        with(binding) {
            progressBar.visibility = View.GONE
            cancelButton.visibility = View.GONE
            goButton.visibility = View.VISIBLE
        }
    }

    private val blurLevel: Int
        get() =
            when (binding.radioBlurGroup.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 2
                R.id.radio_blur_lv_3 -> 3
                else -> 1
            }
}