package com.example.expancom2.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
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

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
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

        var categoryList: List<Category>
        val spinner = binding.categorySpinner

        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        activityViewModel.allCategories.observe(this, Observer { category ->
            category?.let { spinner.adapter = CategorySpinnerAdapter(this, it)}
        })

        Log.d("TAG", "categorylist")

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var itemSelected = p0!!.getItemAtPosition(p2)
                if (itemSelected != null) {
                    var itemSelectedName = (itemSelected as Category).name
                    categoryId = (itemSelected as Category).id
                    Log.d("TAG", categoryId.toString())

                    Toast.makeText(
                        applicationContext,
                        "Вы выбрали $itemSelectedName",
                        Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Выберите категорию",
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

        binding.button.setOnClickListener {
            binding.categoryShortTitle.text = (binding.categorySpinner.selectedItem as Category).name!!.substring(0,1).capitalize()

            if(TextUtils.isEmpty(binding.checkNameText.text) ||
                TextUtils.isEmpty(binding.checkSumText.text) ||
                TextUtils.isEmpty(binding.checkDateTimeText.text)) {
                setResult(Activity.RESULT_CANCELED, Intent())
            } else {

                val check = Check(
                    Random.nextInt(1000),
                    binding.checkNameText.text.toString(),
                    binding.checkSumText.text.toString().toDouble(),
                    savedDay,
                    savedMonth,
                    savedYear,
                    savedHour,
                    savedMinute,
                    categoryId
                )

                activityViewModel.insertCheck(check)
                setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_REPLY, check.toString()))
                startActivity(HistoryActivity::class.java)
            }
            finish()
        }
    }

    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate() {
        binding.selectDateTimeBtn.setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        savedDay = day
        savedMonth = month
        savedYear = year

        TimePickerDialog(this, this, hour, minute, true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        savedHour = hour
        savedMinute = minute

        binding.checkDateTimeText.setText("$savedDay-$savedMonth-$savedYear\n $savedHour:$savedMinute")
        binding.checkDateTime.visibility = View.VISIBLE
    }

}