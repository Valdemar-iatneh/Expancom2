package com.example.expancom2.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.databinding.CategoryResviewItemBinding

class CategoryRecyclerViewAdapter (
    private var context: Context,
    private val categoryList: List<Category>) :
    RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> () {
    private lateinit var binding: CategoryResviewItemBinding

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding = CategoryResviewItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.category_resview_item, parent, false)
        )
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = categoryList[position]
        binding.icon.setImageResource(current.icon)
        binding.icon.setBackgroundColor(Color.parseColor(current.color))
        binding.name.text = current.name
    }

    override fun getItemCount() = categoryList.size
}