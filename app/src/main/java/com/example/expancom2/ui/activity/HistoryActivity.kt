package com.example.expancom2.ui.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import java.util.*
import kotlin.random.Random

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
        Category(6, "Здоровье", 0, "#bbbbbb")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val checkRecyclerView: RecyclerView = binding.checkRecyclerView
        checkRecyclerView.layoutManager = LinearLayoutManager(this)
        //checkRecyclerView.adapter = CheckRecyclerViewAdapter()

        activityViewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        activityViewModel.allChecks.observe(this, Observer { check ->
            check?.let { checkRecyclerView.adapter = CheckRecyclerViewAdapter(it) }
        })
        activityViewModel.insertCategories(categoryData)

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra(AddActivity.EXTRA_REPLY)?.let {
                    val checkLikeList = it.toList()
                    var check = Check(
                        checkLikeList[0].toInt(),
                        checkLikeList[1].toString(),
                        checkLikeList[2].toDouble(),
                        checkLikeList[3].toInt(),
                        checkLikeList[4].toInt(),
                        checkLikeList[5].toInt(),
                        checkLikeList[6].toInt(),
                        checkLikeList[7].toInt(),
                        checkLikeList[8].toInt()
                    )
                    activityViewModel.insertCheck(check)
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Пусто",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(MainActivity@ this, AddActivity::class.java)
            startForResult.launch(intent)
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