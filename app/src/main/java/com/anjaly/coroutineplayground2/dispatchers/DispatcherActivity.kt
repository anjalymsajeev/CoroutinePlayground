package com.anjaly.coroutineplayground2.dispatchers

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anjaly.coroutineplayground2.R
import com.anjaly.coroutineplayground2.databinding.ActivityDispatcherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DispatcherActivity : AppCompatActivity() {
    lateinit var binding: ActivityDispatcherBinding
    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var defaultTextView: TextView
    lateinit var ioTextView: TextView
    lateinit var defaultButton: Button
    lateinit var ioButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDispatcherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind(binding)
    }

    private fun didClickDispatcher() {
        runDispatcherExample()
    }

    private fun didClickIO() {
        fetData()
    }

    private fun didClickDefault() {
        calculateLargeSum()
    }

    fun fetData() {
        lifecycleScope.launch {
            ioTextView.text = getString(R.string.starting)

            val result = withContext(Dispatchers.IO) {
                Thread.sleep(1000)
                "User Data Loaded"
            }
            ioTextView.text = result
        }
    }

    fun calculateLargeSum() {
        lifecycleScope.launch {
            defaultTextView.text = getString(R.string.calculating)
            val result = withContext(Dispatchers.Default) {
                var sum = 0
                for (i in 1..1000000000) {
                    sum += i
                }
                sum
            }
            defaultTextView.text = "Result${result}"
        }
    }

    private fun runDispatcherExample() {
        lifecycleScope.launch {
            val mainThread = "MainThread: ${Thread.currentThread().name}"

            val ioThread = withContext(Dispatchers.IO) {
                "IO Thread:${Thread.currentThread().name}"
            }

            val defaultThread = withContext(Dispatchers.Default) {
                "Default Thread:${Thread.currentThread().name}"
            }

            textView.text = buildString {
                append("$mainThread\n")
                append("$ioThread\n $defaultThread\n")
            }

        }
    }

    private fun bind(binding: ActivityDispatcherBinding) {
        textView = binding.activityDispatchersTextView
        button = binding.activityDispatchersButton
        defaultTextView = binding.activityDispatchersTextView2
        ioTextView = binding.activityDispatchersTextView1
        ioButton = binding.activityDispatchersButton1
        defaultButton = binding.activityDispatchersButton2
        button.setOnClickListener { didClickDispatcher() }
        ioButton.setOnClickListener { didClickIO() }
        defaultButton.setOnClickListener { didClickDefault() }
    }
}
