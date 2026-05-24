package com.anjaly.coroutineplayground2.timeout

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anjaly.coroutineplayground2.databinding.ActivityTimeoutBinding
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

class TimeoutActivity : AppCompatActivity() {
    lateinit var binding: ActivityTimeoutBinding
    lateinit var resultTextview: TextView
    lateinit var startButton: Button
    lateinit var startOrNullButton: Button
    lateinit var retryButton: Button
    lateinit var retryAgainButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    private fun didClickStart() {
        startApiCall()
    }


    private fun didClickStartOrNullButton() {
        startApiCallWithNull()
    }

    private fun didClickRetryButton() {
        startApiCall()
    }

    private fun didClickRetryAgainButton() {
        retryApiCall()
    }

    fun startApiCall() {
        try {
            retryButton.visibility = GONE
            resultTextview.text = "loading"
            lifecycleScope.launch {
                withTimeout(3000) {
                    delay(5000)
                }
                resultTextview.text = "API Success"
            }
        } catch (e: TimeoutCancellationException) {
            Log.d("TimeoutActivity", "startApiCall: ${e.message}")
            runOnUiThread {
                resultTextview.text = "Requested cancellation$e"
                retryButton.visibility = VISIBLE
            }
        }
    }

    fun startApiCallWithNull() {
        retryButton.visibility = GONE
        resultTextview.text = "loading"
        lifecycleScope.launch {
            val result = withTimeoutOrNull(3000) {
                delay(5000)
                "API Success"
            }
            resultTextview.text = result ?: "Requested TimeOut"
            retryButton.visibility = if (result == null) VISIBLE else GONE
        }
    }

    private fun retryApiCall() {
        resultTextview.text = "starting"
        var success = false
        lifecycleScope.launch {
            repeat(3) { attempt ->
                val result = withTimeoutOrNull(3000) {
                    delay(5000)
                    "sucess"
                }
                if (result != null) {
                    success = true
                    resultTextview.text = "Sucess After Attempt $attempt"
                    return@launch
                }
                resultTextview.text = "Attempt $attempt failed"

                if (!success) {
                    resultTextview.text = "All attempt Failed"
                }
            }
        }
    }

    fun bind() {
        resultTextview = binding.activityTimeoutResultTextView
        startButton = binding.activityTimeoutStartButton
        startOrNullButton = binding.activityTimeoutStartOrNullButton
        retryButton = binding.activityTimeoutRetryButton
        retryAgainButton = binding.activityTimeoutRetryAgainButton

        startButton.setOnClickListener { didClickStart() }
        startOrNullButton.setOnClickListener { didClickStartOrNullButton() }
        retryButton.setOnClickListener { didClickRetryButton() }
        retryAgainButton.setOnClickListener { didClickRetryAgainButton() }
    }
}