package com.example.expancom2.ui.activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.databinding.ActivityMainBinding
import com.example.expancom2.ui.common.ActivityViewModel
import com.example.expancom2.ui.common.BaseActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.color.DynamicColorsOptions


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var categoryList: List<Category>

    private var cat1 = Category(1, "Продукты", 10.0, "#6600ff")
    private var cat2 = Category(2, "Досуг", 20.0, "#30d5c8")
    private var cat3 = Category(3, "Кафе", 30.0, "#9b2d30")
    private var cat4 = Category(4, "Транспорт", 15.0, "#fde910")
    private var cat5 = Category(5, "Здоровье", 20.0, "#8b0000")
    private var cat6 = Category(6, "Семья и дом", 5.0, "#ffa500")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.description.isEnabled = false
        binding.pieChart.setExtraOffsets(5f,10f,5f,5f)

        binding.pieChart.dragDecelerationFrictionCoef = 0.95f

        binding.pieChart.isDrawHoleEnabled = true
        binding.pieChart.setHoleColor(Color.WHITE)

        binding.pieChart.setTransparentCircleColor(Color.WHITE)
        binding.pieChart.setTransparentCircleAlpha(110)

        binding.pieChart.holeRadius = 58f
        binding.pieChart.transparentCircleRadius = 61f

        binding.pieChart.setDrawCenterText(true)

        binding.pieChart.rotationAngle = 0f

        binding.pieChart.isRotationEnabled = true
        binding.pieChart.isHighlightPerTapEnabled = true

        binding.pieChart.animateY(1400, Easing.EaseInOutQuad)

        binding.pieChart.legend.isEnabled = false
        binding.pieChart.setEntryLabelColor(Color.WHITE)
        binding.pieChart.setEntryLabelTextSize(12f)

        val colors: ArrayList<Int> = ArrayList()

        val sums: ArrayList<Float> = ArrayList()

        var fullSum: Double = 0.0

        val entries: ArrayList<PieEntry> = ArrayList()

        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        categoryList = emptyList()
        activityViewModel.allCategories.observe(this, Observer { category ->
            category?.let {
                categoryList = it

                for (i in categoryList) {
                    fullSum += i.sum
                }
                for (i in categoryList) {
                    if (i.sum == 0.0) {
                        i.sum = 1.0
                        val t = i.sum * 100 / fullSum
                        sums.add((t).toFloat())
                        colors.add(Color.parseColor(i.color))
                    } else {
                        val t = i.sum * 100 / fullSum
                        sums.add((t).toFloat())
                        colors.add(Color.parseColor(i.color))
                        Log.d("sum", t.toString())
                    }
                }

                for (i in sums) {
                    entries.add(PieEntry(i))
                }
            }
        })

        activityViewModel.insertCategory(cat1)
        activityViewModel.insertCategory(cat2)
        activityViewModel.insertCategory(cat3)
        activityViewModel.insertCategory(cat4)
        activityViewModel.insertCategory(cat5)
        activityViewModel.insertCategory(cat6)

        val dataSet = PieDataSet(entries, "Categories")
        dataSet.setDrawIcons(false)

        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        //val colors: ArrayList<Int> = ArrayList()
        //colors.add(Color.parseColor("#CE93D8"))
        //colors.add(Color.parseColor("#F0E68C"))
        //colors.add(Color.parseColor("#DC143C"))

        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        binding.pieChart.data = data

        binding.pieChart.highlightValues(null)

        binding.pieChart.invalidate()
    }
}