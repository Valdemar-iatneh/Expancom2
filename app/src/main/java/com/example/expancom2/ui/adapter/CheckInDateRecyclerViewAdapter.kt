package com.example.expancom2.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.data.roomdb.entity.Check
import com.example.expancom2.databinding.CategoryResviewItemBinding
import com.example.expancom2.databinding.CheckResviewItemBinding
import kotlin.system.measureNanoTime

class CheckInDateRecyclerViewAdapter(
    private var context: Context,
    private val checkList: List<Check>,
    private val categoryList: List<Category>) :

    RecyclerView.Adapter<CheckInDateRecyclerViewAdapter.CheckInDateViewHolder>() {
    private lateinit var binding: CheckResviewItemBinding

    class CheckInDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckInDateViewHolder {
        binding = CheckResviewItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.check_resview_item, parent, false)
        )
        return CheckInDateViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CheckInDateViewHolder, position: Int) {
        val current = checkList[position]
        binding.categoryIcon.setImageResource(getCategoryIconById(current.categoryId))
        binding.categoryIcon.setBackgroundColor(Color.parseColor(getCategoryColorById(current.categoryId)))
        binding.checkName.text = current.name
        binding.categoryName.text = getCategoryNameById(current.categoryId)
        val checkSumText = "-" + current.sum.toString()
        binding.checkSum.text = checkSumText
    }

    override fun getItemCount() = checkList.size

    private fun getCategoryNameById(id: Int): String? {
        return categoryList.first { it.id == id}.name
    }

    private fun getCategoryIconById(id: Int): Int {
        return categoryList.first { it.id == id}.icon
    }

    private fun getCategoryColorById(id: Int): String {
        return categoryList.first { it.id == id}.color
    }
}