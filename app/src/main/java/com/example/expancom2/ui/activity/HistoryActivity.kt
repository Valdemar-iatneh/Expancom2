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

    private var cat1 = Category(1, "Продукты", 0.0, "#6600ff", R.drawable.product_icon)
    private var cat2 = Category(2, "Досуг", 0.0, "#30d5c8", R.drawable.entertainment_icon)
    private var cat3 = Category(3, "Кафе", 0.0, "#9b2d30", R.drawable.food_icon)
    private var cat4 = Category(4, "Транспорт", 0.0, "#fde910", R.drawable.transport_icon)
    private var cat5 = Category(5, "Здоровье", 0.0, "#8b0000", R.drawable.medicine_icon)
    private var cat6 = Category(6, "Семья и дом", 0.0, "#ffa500", R.drawable.family_icon)

    //private val date: Calendar = Calendar.getInstance()
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

        val date: Calendar = Calendar.getInstance()
        date.set(2022, 11, 12, 2, 2)
        val checkItem1 = Check(Random.nextInt(1000), "Книга", 100.0, date.time, 1)
        val checkItem2 = Check(Random.nextInt(1000), "Монитор", 200.0, date.time, 2)
        val checkItem3 = Check(Random.nextInt(1000), "Огниво", 300.0, date.time, 3)
        val checkItem4 = Check(Random.nextInt(1000), "Окно", 400.0, date.time, 4)
        val checkItem5 = Check(Random.nextInt(1000), "Телефон", 500.0, date.time, 5)
        val checkItem6 = Check(Random.nextInt(1000), "Такси", 600.0, date.time, 6)

        activityViewModel.allCategories.observe(this, Observer { category ->
            category?.let {
                categoryList = it

                //activityViewModel.insertCheck(checkItem1)
                //val categorySelected1 = categoryList.first { it.id == checkItem1.categoryId}
                //categorySelected1.sum += checkItem1.sum
                //activityViewModel.updateCategory(categorySelected1)
//
                //activityViewModel.insertCheck(checkItem2)
                //val categorySelected2 = categoryList.first { it.id == checkItem2.categoryId}
                //categorySelected2.sum += checkItem2.sum
                //activityViewModel.updateCategory(categorySelected2)
//
                //activityViewModel.insertCheck(checkItem3)
                //val categorySelected3 = categoryList.first { it.id == checkItem3.categoryId}
                //categorySelected3.sum += checkItem3.sum
                //activityViewModel.updateCategory(categorySelected3)
//
                //activityViewModel.insertCheck(checkItem4)
                //val categorySelected4 = categoryList.first { it.id == checkItem4.categoryId}
                //categorySelected4.sum += checkItem4.sum
                //activityViewModel.updateCategory(categorySelected4)
//
                //activityViewModel.insertCheck(checkItem5)
                //val categorySelected5 = categoryList.first { it.id == checkItem5.categoryId}
                //categorySelected5.sum += checkItem5.sum
                //activityViewModel.updateCategory(categorySelected5)
//
                //activityViewModel.insertCheck(checkItem6)
                //val categorySelected6 = categoryList.first { it.id == checkItem6.categoryId}
                //categorySelected6.sum += checkItem6.sum
                //activityViewModel.updateCategory(categorySelected6)
            }
        })
        activityViewModel.allChecks.observe(this, Observer { check ->
            check?.let { checkRecyclerView.adapter = CheckRecyclerViewAdapter(this, it, categoryList) }
        })

        //activityViewModel.insertCategory(cat1)
        //activityViewModel.insertCategory(cat2)
        //activityViewModel.insertCategory(cat3)
        //activityViewModel.insertCategory(cat4)
        //activityViewModel.insertCategory(cat5)
        //activityViewModel.insertCategory(cat6)


        binding.addBtn.setOnClickListener {
            startActivity(AddActivity::class.java)
        }

        binding.diagramBtn.setOnClickListener {
            //if (checkList.isEmpty()) {
            //    Toast.makeText(
            //        applicationContext,
            //        "Чтобы открыть статистику, добавьте чеки",
            //        Toast.LENGTH_LONG
            //    ).show()
            //} else {
            //    startActivity(MainActivity::class.java)
            //}
            startActivity(MainActivity::class.java)
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