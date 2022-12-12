package com.example.expancom2.ui.activity

import android.app.Dialog
import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check
import com.example.expancom2.databinding.ActivityHistoryBinding
import com.example.expancom2.databinding.DeleteDialogBinding
import com.example.expancom2.ui.adapter.CheckRecyclerViewAdapter
import com.example.expancom2.ui.common.ActivityViewModel
import com.example.expancom2.ui.common.BaseActivity
import java.util.Calendar
import kotlin.random.Random

class HistoryActivity : BaseActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var bindingDelete: DeleteDialogBinding
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var deleteDialog: Dialog
    private lateinit var categoryList: List<Category>
    private lateinit var checkList: List<Check>

    private var cat1 = Category(1, "Продукты", 0.0, "#6600ff")
    private var cat2 = Category(2, "Досуг", 0.0, "#30d5c8")
    private var cat3 = Category(3, "Кафе", 0.0, "#9b2d30")
    private var cat4 = Category(4, "Транспорт", 0.0, "#fde910")
    private var cat5 = Category(5, "Здоровье", 0.0, "#8b0000")
    private var cat6 = Category(6, "Семья и дом", 0.0, "#ffa500")

    private val date: Calendar = Calendar.getInstance()
    //private var checkItem6 = Check(Random.nextInt(1000), "Монитор", 100.0, date.set(2022, 11, 12, 2, 2), 1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val checkRecyclerView: RecyclerView = binding.checkRecyclerView
        checkRecyclerView.layoutManager = LinearLayoutManager(this)

        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        categoryList = emptyList()
        checkList = emptyList()
        activityViewModel.allCategories.observe(this, Observer { category ->
            category?.let { categoryList = it}
        })
        activityViewModel.allChecks.observe(this, Observer { check ->
            check?.let { checkRecyclerView.adapter = CheckRecyclerViewAdapter(this, it, categoryList) }
        })

        activityViewModel.insertCategory(cat1)
        activityViewModel.insertCategory(cat2)
        activityViewModel.insertCategory(cat3)
        activityViewModel.insertCategory(cat4)
        activityViewModel.insertCategory(cat5)
        activityViewModel.insertCategory(cat6)

        binding.fab.setOnClickListener {
            startActivity(AddActivity::class.java)
        }

        binding.materialButtonDelete.setOnClickListener {
            activityViewModel.delete()
        }

        binding.materialButtonDeleteCheck.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this@HistoryActivity)
            bindingDelete = DeleteDialogBinding.inflate(LayoutInflater.from(this))
            dialogBuilder.setView(bindingDelete.root)
            dialogBuilder.setPositiveButton("Submit") { _, _ ->
                activityViewModel.deleteCheck(bindingDelete.etView.text.toString())
            }
        dialogBuilder.create().show()
        }
    }
}