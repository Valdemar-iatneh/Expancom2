package com.example.expancom2.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.databinding.ActivityMainBinding
import com.example.expancom2.ui.common.ActivityViewModel
import com.example.expancom2.ui.common.BaseActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.color.DynamicColorsOptions
import java.util.Random


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var categoryList: List<Category>

    private var cat1 = Category(1, "Продукты", 0.0, "#6600ff", R.drawable.product_icon)
    private var cat2 = Category(2, "Досуг", 0.0, "#30d5c8", R.drawable.entertainment_icon)
    private var cat3 = Category(3, "Кафе", 0.0, "#9b2d30", R.drawable.food_icon)
    private var cat4 = Category(4, "Транспорт", 0.0, "#fde910", R.drawable.transport_icon)
    private var cat5 = Category(5, "Здоровье", 0.0, "#8b0000", R.drawable.medicine_icon)
    private var cat6 = Category(6, "Семья и дом", 0.0, "#ffa500", R.drawable.family_icon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]

        initPieChart()

        binding.addBtn.setOnClickListener {
            startActivity(AddActivity::class.java)
        }

        binding.diagramBtn.setOnClickListener {
            startActivity(MainActivity::class.java)
            setResult(Activity.RESULT_OK, Intent().putExtra(AddActivity.EXTRA_REPLY, "sas"))
            finish()
        }
    }

    private fun initPieChart() {
        val entries: ArrayList<PieEntry> = ArrayList()
        val colors: ArrayList<Int> = ArrayList()
        val sums: ArrayList<Float> = ArrayList()
        var fullSum: Double = 0.0
        val rnd = Random()

        //val dataSet = PieDataSet(entries, "Categories")
        //val data = PieData(dataSet)

        var pieChartC: PieChart = binding.pieChart

        pieChartC.setUsePercentValues(true)
        pieChartC.description.isEnabled = false
        pieChartC.setExtraOffsets(5f,10f,5f,5f)

        pieChartC.dragDecelerationFrictionCoef = 0.95f

        pieChartC.isDrawHoleEnabled = true
        pieChartC.setHoleColor(Color.WHITE)

        pieChartC.setTransparentCircleColor(Color.WHITE)
        pieChartC.setTransparentCircleAlpha(110)

        pieChartC.holeRadius = 58f
        pieChartC.transparentCircleRadius = 61f

        pieChartC.setDrawCenterText(true)

        pieChartC.rotationAngle = 0f

        pieChartC.isRotationEnabled = true
        pieChartC.isHighlightPerTapEnabled = true

        pieChartC.animateY(1400, Easing.EaseInOutQuad)

        pieChartC.legend.isEnabled = false
        pieChartC.setEntryLabelColor(Color.WHITE)
        pieChartC.setEntryLabelTextSize(12f)

        categoryList = emptyList()
        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        activityViewModel.allCategories.observe(this, Observer { category ->
            category?.let {
                categoryList = it

                for (i in categoryList) {
                    fullSum += i.sum
                    Log.d("fullSum", fullSum.toString())
                }
                for (i in categoryList) {
                    if (i.sum == 0.0) {


                        //i.sum = 1.0
                        //val t = i.sum * 100 / fullSum
                        //sums.add((t).toFloat())
                        //colors.add(Color.parseColor(i.color))
                    } else {
                        //val t = i.sum * 100 / fullSum
                        //Log.d("sumT", t.toString())
                        //Log.d("sumE", t)
                        //sums.add(i.sum.toFloat())
                        entries.add(PieEntry(i.sum.toFloat(), i.name))
                        colors.add(Color.parseColor(i.color))
                        //colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
                    }
                }

                //for (i in sums) {
                //    //entries.add(PieEntry(20f))
                //    entries.add(PieEntry(i))
                //}

                val dataSet = PieDataSet(entries, "Categories")
                dataSet.setDrawIcons(false)

                dataSet.sliceSpace = 3f
                dataSet.iconsOffset = MPPointF(0f, 40f)
                dataSet.selectionShift = 5f
                dataSet.colors = colors

                val data = PieData(dataSet)
                data.setValueFormatter(PercentFormatter())
                data.setValueTextSize(15f)
                data.setValueTypeface(Typeface.DEFAULT_BOLD)
                data.setValueTextColor(Color.WHITE)

                pieChartC.data = data

            }
        })

        //entries.add(PieEntry(263232f, "lox"))
        //entries.add(PieEntry(203385f, "chek"))
        //entries.add(PieEntry(303385f, "tyt"))
        //colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))
        //colors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)))




        pieChartC.highlightValues(null)

        pieChartC.invalidate()
    }
}