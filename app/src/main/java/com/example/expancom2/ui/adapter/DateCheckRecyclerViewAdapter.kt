package com.example.expancom2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check
import com.example.expancom2.databinding.DatetimeResviewItemBinding
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration.Companion.days

class DateCheckRecyclerViewAdapter(
    private var context: Context,
    private var checkList: List<Check>,
    private var categoryList: List<Category>):
    RecyclerView.Adapter<DateCheckRecyclerViewAdapter.DateCheckViewHolder>() {
    private lateinit var binding: DatetimeResviewItemBinding

    class DateCheckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateCheckViewHolder {
        binding = DatetimeResviewItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.datetime_resview_item, parent, false)
        )
        return DateCheckViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DateCheckViewHolder, position: Int) {
        val checkListFiltered = checkList.sortedByDescending { it.date }
        val current = checkListFiltered[position]
        val cal = Calendar.getInstance()
        cal.time = current.date
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = getRusMonth(cal.get(Calendar.MONTH))
        val dayMonth = "$day $month"
        binding.date.text = dayMonth
        binding.checkResView.layoutManager = LinearLayoutManager(context)
        binding.checkResView.adapter = CheckInDateRecyclerViewAdapter(context, getChecksByDate(current.date), categoryList)
    }

    override fun getItemCount() = checkList.size

    private fun getChecksByDate(date: Date) : List<Check> {
        val cal1 = Calendar.getInstance()
        cal1.time = date
        val day1 = cal1.get(Calendar.DAY_OF_MONTH)
        val month1 = cal1.get(Calendar.MONTH)
        return checkList.filter {
            val cal2 = Calendar.getInstance()
            cal2.time = it.date
            val day2 = cal2.get(Calendar.DAY_OF_MONTH)
            val month2 = cal2.get(Calendar.MONTH)
            (day1 == day2) and (month1 == month2)
        }
    }

    private fun getRusMonth(month: Int) =
        when (month) {
            0 -> "Января"
            1 -> "Февраля"
            2 -> "Марта"
            3 -> "Апреля"
            4 -> "Мая"
            5 -> "Июня"
            6 -> "Июля"
            7 -> "Августа"
            8 -> "Сентября"
            9 -> "Октября"
            10 -> "Ноября"
            11 -> "Декабря"
            else -> "Некорректный месяц"
        }
}