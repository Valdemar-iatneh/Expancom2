package com.example.expancom2.ui.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    fun startActivity(classActivity: Class<*>) {
        startActivity(Intent(applicationContext, classActivity))
    }

    fun startActivityWithFinish(classActivity: Class<*>) {
        startActivity(Intent(applicationContext, classActivity))
        finish()
    }
}