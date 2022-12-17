package com.example.expancom2.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.databinding.ActivitySplashBinding
import com.example.expancom2.ui.common.ActivityViewModel
import com.example.expancom2.ui.common.BaseActivity

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    private lateinit var activityViewModel: ActivityViewModel

    private var cat1 = Category(1, "Продукты", 0.0, "#1b90bb", R.drawable.product_icon)
    private var cat2 = Category(2, "Досуг", 0.0, "#30bca1", R.drawable.entertainment_icon)
    private var cat3 = Category(3, "Кафе", 0.0, "#e398ac", R.drawable.food_icon)
    private var cat4 = Category(4, "Транспорт", 0.0, "#e3ae1b", R.drawable.transport_icon)
    private var cat5 = Category(5, "Здоровье", 0.0, "#951937", R.drawable.medicine_icon)
    private var cat6 = Category(6, "Семья и дом", 0.0, "#ef8264", R.drawable.family_icon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]

        activityViewModel.insertCategory(cat1)
        activityViewModel.insertCategory(cat2)
        activityViewModel.insertCategory(cat3)
        activityViewModel.insertCategory(cat4)
        activityViewModel.insertCategory(cat5)
        activityViewModel.insertCategory(cat6)

        Handler(Looper.myLooper()!!).postDelayed({
            startActivityWithFinish(HistoryActivity::class.java)
        }, 2000)
    }
}