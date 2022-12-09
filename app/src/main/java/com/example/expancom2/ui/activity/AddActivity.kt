package com.example.expancom2.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import com.example.expancom2.databinding.ActivityAddBinding
import com.example.expancom2.ui.common.BaseActivity

class AddActivity : BaseActivity() {
    private lateinit var binding: ActivityAddBinding

    companion object {
        const val EXTRA_REPLY = "com.example.expancom2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.button.setOnClickListener {
            binding.categoryShortTitle.text = binding.checkName.text!!.substring(0,1).capitalize()

            if(TextUtils.isEmpty(binding.checkName.text)) {
                setResult(Activity.RESULT_CANCELED, Intent())
            } else {
                val check = binding.checkName.text.toString()
                setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_REPLY, check))
            }
            finish()
        }
    }
}