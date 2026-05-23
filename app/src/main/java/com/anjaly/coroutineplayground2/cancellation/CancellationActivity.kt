package com.anjaly.coroutineplayground2.cancellation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anjaly.coroutineplayground2.R
import com.anjaly.coroutineplayground2.databinding.ActivityCancellationBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CancellationActivity : AppCompatActivity() {
    lateinit var binding: ActivityCancellationBinding
    lateinit var startDownLoad: Button
    lateinit var stopDownLoad: Button
    lateinit var textView: TextView
    var jobDownLoad: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancellationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    private fun didClickStartDownLoad() {
        startDownLoad()
    }

    private fun didClickEndDownLoad() {
        stopDownLoad()
    }

    @SuppressLint("SetTextI18n")
    fun startDownLoad() {
        if (jobDownLoad?.isActive == true) {
            textView.text = "DownLoad Already running"
            return
        }
        jobDownLoad = lifecycleScope.launch {
            for (i in 1..100 step 10) {
                delay(1000)
                textView.text = "$i%"
            }
        }
        textView.text="DownLoad Completed"
    }

    fun stopDownLoad() {
        jobDownLoad?.cancel()
        textView.text = getString(R.string.download_cancelled)
    }

    fun bind() {
        startDownLoad = binding.activityCancellationStartButton
        stopDownLoad = binding.activityCancellationStopButton
        textView = binding.activityCancellationTextView

        startDownLoad.setOnClickListener { didClickStartDownLoad() }
        stopDownLoad.setOnClickListener { didClickEndDownLoad() }
    }
}
