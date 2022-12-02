package com.example.expancom2.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.example.expancom2.databinding.ActivitySplashBinding
import com.example.expancom2.ui.common.BaseActivity

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            startActivityWithFinish(MainActivity::class.java)
        }, 2000)
    }
}