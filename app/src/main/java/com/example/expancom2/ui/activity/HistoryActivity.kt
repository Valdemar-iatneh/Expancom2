package com.example.expancom2.ui.activity

import android.app.Dialog
import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check
import com.example.expancom2.databinding.ActivityHistoryBinding
import com.example.expancom2.databinding.DeleteDialogBinding
import com.example.expancom2.ui.adapter.CheckInDateRecyclerViewAdapter
import com.example.expancom2.ui.adapter.CheckRecyclerViewAdapter
import com.example.expancom2.ui.adapter.DateCheckRecyclerViewAdapter
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
            category?.let {
                categoryList = it
            }
        })
        activityViewModel.allChecks.observe(this, Observer { check ->
            check?.let { checkRecyclerView.adapter = DateCheckRecyclerViewAdapter(this, it, categoryList) }
        })

        binding.addBtn.setOnClickListener {
            startActivity(AddActivity::class.java)
        }

        binding.diagramBtn.setOnClickListener {
            startActivity(MainActivity::class.java)
        }

        binding.deleteBtn.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this@HistoryActivity)
            bindingDelete = DeleteDialogBinding.inflate(LayoutInflater.from(this))
            dialogBuilder.setView(bindingDelete.root)
            dialogBuilder.setPositiveButton("Удалить") { _, _ ->
                activityViewModel.deleteCheck(bindingDelete.etView.text.toString())
            }
            dialogBuilder.create().show()
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