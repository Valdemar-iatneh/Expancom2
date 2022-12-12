package com.example.expancom2.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check
import com.example.expancom2.databinding.ActivityAddBinding
import com.example.expancom2.ui.adapter.CategorySpinnerAdapter
import com.example.expancom2.ui.adapter.CheckRecyclerViewAdapter
import com.example.expancom2.ui.common.ActivityViewModel
import com.example.expancom2.ui.common.BaseActivity
import java.util.*
import kotlin.random.Random

class AddActivity : BaseActivity(),
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private lateinit var binding: ActivityAddBinding
    private lateinit var activityViewModel: ActivityViewModel
    private val cal: Calendar = Calendar.getInstance()

    var savedDay = 1
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    var categoryId = 0

    companion object {
        const val EXTRA_REPLY = "com.example.expancom2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        pickDate()

        val spinner = binding.categorySpinner
        var categoryList = emptyList<Category>()

        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        activityViewModel.allCategories.observe(this, Observer { category ->
            category?.let {
                spinner.adapter = CategorySpinnerAdapter(this, it)
                categoryList = it
            }
        })

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val itemSelected = p0!!.getItemAtPosition(p2)
                if (itemSelected != null) {
                    val itemSelectedName = (itemSelected as Category).name
                    binding.selectTV.visibility = View.GONE
                    categoryId = itemSelected.id
                    Toast.makeText(
                        applicationContext,
                        "Вы выбрали $itemSelectedName",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    applicationContext,
                    "Ничего не выбрано",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.saveBtn.setOnClickListener {
            binding.categoryShortTitle.text = (binding.categorySpinner.selectedItem as Category).name!!.substring(0,1).capitalize()

            if(TextUtils.isEmpty(binding.checkNameText.text) ||
                TextUtils.isEmpty(binding.checkSumText.text) ||
                TextUtils.isEmpty(binding.checkDateTimeText.text)) {

            } else {
                val check = Check(
                    Random.nextInt(1000),
                    binding.checkNameText.text.toString(),
                    binding.checkSumText.text.toString().toDouble(),
                    cal.time,
                    categoryId
                )

                val categorySelected = categoryList.first { it.id == categoryId}

                categorySelected.sum += binding.checkSumText.text.toString().toDouble()
                activityViewModel.updateCategory(categorySelected)
                Log.d("cat", categorySelected.toString())

                activityViewModel.insertCheck(check)
                setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_REPLY, check.toString()))
            }
            finish()
        }
    }

    private fun pickDate() {
        binding.selectDateTimeBtn.setOnClickListener {
            DatePickerDialog(
                this,
                this,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cal.set(year, month, dayOfMonth, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE))

        TimePickerDialog(
            this,
            this,
            cal.get(Calendar.HOUR),
            cal.get(Calendar.MINUTE),
            true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hourOfDay, minute)
        val time = cal.time.toString()

        binding.checkDateTimeText.setText(time)
        binding.checkDateTime.visibility = View.VISIBLE
    }

}