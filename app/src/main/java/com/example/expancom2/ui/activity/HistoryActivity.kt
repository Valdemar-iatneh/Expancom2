package com.example.expancom2.ui.activity

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.databinding.ActivityHistoryBinding
import com.example.expancom2.databinding.DeleteDialogBinding
import com.example.expancom2.ui.adapter.CheckRecyclerViewAdapter
import com.example.expancom2.ui.common.ActivityViewModel
import com.example.expancom2.ui.common.BaseActivity

class HistoryActivity : BaseActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var bindingDelete: DeleteDialogBinding
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var deleteDialog: Dialog

    private var categoryData = listOf(
        Category(1, "Продукты", 0, "#000000"),
        Category(2, "Досуг", 0, "#aaaaaa"),
        Category(3, "Кафе", 0, "#cccccc"),
        Category(4, "Транспорт", 0, "#555555"),
        Category(5, "Здоровье", 0, "#eeeeee"),
        Category(6, "Семья и дом", 0, "#bbbbbb")
    )

    private var cat1 = Category(1, "Продукты", 0, "#000000")
    private var cat2 = Category(2, "Досуг", 0, "#aaaaaa")
    private var cat3 = Category(3, "Кафе", 0, "#cccccc")
    private var cat4 = Category(4, "Транспорт", 0, "#555555")
    private var cat5 = Category(5, "Здоровье", 0, "#eeeeee")
    private var cat6 = Category(6, "Семья и дом", 0, "#bbbbbb")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val checkRecyclerView: RecyclerView = binding.checkRecyclerView
        checkRecyclerView.layoutManager = LinearLayoutManager(this)

        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        activityViewModel.allChecks.observe(this, Observer { check ->
            check?.let { checkRecyclerView.adapter = CheckRecyclerViewAdapter(it) }
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
        val b = dialogBuilder.create()
        b.show()
        }
    }
}