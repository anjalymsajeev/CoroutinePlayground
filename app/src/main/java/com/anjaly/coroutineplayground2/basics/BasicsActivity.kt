package com.anjaly.coroutineplayground2.basics

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anjaly.coroutineplayground2.databinding.ActivityBasicsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BasicsActivity : AppCompatActivity() {
    lateinit var binding: ActivityBasicsBinding
    lateinit var counterButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicsBinding.inflate(layoutInflater)
        bind(binding)
        setContentView(binding.root)
    }

    private fun didClickCounterButton() {
        lifecycleScope.launch {
            repeat(5) {
                delay(1000)
                Log.d("TAG", "$it")
            }
        }
    }

    private fun bind(binding: ActivityBasicsBinding) {
        counterButton = binding.activityBasicsCounter

        counterButton.setOnClickListener { didClickCounterButton() }
    }
}
