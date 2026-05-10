package com.anjaly.coroutineplayground2.asyncawait

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anjaly.coroutineplayground2.R
import com.anjaly.coroutineplayground2.databinding.ActivityAsyncAwaitBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AsyncAwaitActivity : AppCompatActivity() {
    lateinit var binding: ActivityAsyncAwaitBinding
    lateinit var resultTextView: TextView
    lateinit var fetchButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncAwaitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind(binding)
    }

    fun didClickFetchButton() {
        fetchData()
    }

    fun fetchData() {
        lifecycleScope.launch {
            resultTextView.text = getString(R.string.loading)

            val userDeferred = async {
                delay(1000)

                "user Loaded"
            }

            val postDeferred = async {
                delay(2000)
                "post loaded"
            }

            val result = """
                ${userDeferred.await()}
                ${postDeferred.await()}
            """.trimIndent()

            resultTextView.text = result
        }
    }

    private fun bind(binding: ActivityAsyncAwaitBinding) {
        resultTextView = binding.resultTextView
        fetchButton = binding.fetchButton

        fetchButton.setOnClickListener { didClickFetchButton() }
    }
}