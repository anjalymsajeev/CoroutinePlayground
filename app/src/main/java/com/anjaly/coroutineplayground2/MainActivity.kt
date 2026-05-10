package com.anjaly.coroutineplayground2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.anjaly.coroutineplayground2.basics.BasicsActivity
import com.anjaly.coroutineplayground2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var basicButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }

    fun didClickBasicButton() {
        startActivity(Intent(this, BasicsActivity::class.java))
    }

    fun bind() {
        basicButton = binding.activityMainBasic

        basicButton.setOnClickListener { didClickBasicButton() }
    }
}