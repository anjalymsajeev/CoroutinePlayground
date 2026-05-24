package com.anjaly.coroutineplayground2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.anjaly.coroutineplayground2.asyncawait.AsyncAwaitActivity
import com.anjaly.coroutineplayground2.basics.BasicsActivity
import com.anjaly.coroutineplayground2.cancellation.CancellationActivity
import com.anjaly.coroutineplayground2.databinding.ActivityMainBinding
import com.anjaly.coroutineplayground2.dispatchers.DispatcherActivity
import com.anjaly.coroutineplayground2.timeout.TimeoutActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var basicButton: Button
    lateinit var asyncButton: Button
    lateinit var dispatchersButton: Button
    lateinit var cancellationButton: Button
    lateinit var timeoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    fun didClickBasicButton() {
        startActivity(Intent(this, BasicsActivity::class.java))
    }

    fun didClickAsyncButton() {
        startActivity(Intent(this, AsyncAwaitActivity::class.java))
    }

    private fun didClickDispatchersButton() {
        startActivity(Intent(this, DispatcherActivity::class.java))
    }

    private fun didClickCancellationButton() {
        startActivity(Intent(this, CancellationActivity::class.java))
    }

    private fun didClickTimeoutButton(){
        startActivity(Intent(this, TimeoutActivity::class.java))
    }

    fun bind() {
        basicButton = binding.activityMainBasic
        asyncButton = binding.activityMainAsyn
        dispatchersButton = binding.activityMainDispatchers
        cancellationButton = binding.activityMainCancellation
        timeoutButton = binding.activityMainTimeOut

        basicButton.setOnClickListener { didClickBasicButton() }
        asyncButton.setOnClickListener { didClickAsyncButton() }
        dispatchersButton.setOnClickListener { didClickDispatchersButton() }
        cancellationButton.setOnClickListener { didClickCancellationButton() }
        timeoutButton.setOnClickListener { didClickTimeoutButton() }
    }
}
