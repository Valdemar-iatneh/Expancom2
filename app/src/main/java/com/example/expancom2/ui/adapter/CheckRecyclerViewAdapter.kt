package com.example.expancom2.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.R
import com.example.expancom2.data.AppRepository
import com.example.expancom2.data.roomdb.DBService
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check
import com.example.expancom2.databinding.RecyclerviewItemBinding
import com.example.expancom2.ui.common.ActivityViewModel
import java.util.*

class CheckRecyclerViewAdapter(
    private var context: Context,
    private val checkList: List<Check>,
    private val categoryList: List<Category>) :

    RecyclerView.Adapter<CheckRecyclerViewAdapter.CheckViewHolder>() {
    private lateinit var binding: RecyclerviewItemBinding

    class CheckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckViewHolder {
        binding = RecyclerviewItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        )
        return CheckViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CheckViewHolder, position: Int) {
        val current = checkList[position]
        binding.checkName.text = current.name
        binding.checkSum.text = current.sum.toString()
        binding.checkCategory.text = getCategoryNameById(current.categoryId)
        binding.checkCategorySum.text = getCategorySumById(current.categoryId)
        binding.checkDateTime.text = current.date.toString()
        binding.tcCircle.text = binding.checkName.text.substring(0,1).capitalize()
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        binding.card.setBackgroundColor(color)
    }

    override fun getItemCount() = checkList.size

    private fun getCategoryNameById(id: Int): String? {
        return categoryList.first { it.id == id}.name
    }

    private fun getCategorySumById(id: Int): String? {
        return categoryList.first { it.id == id}.sum.toString()
    }
}