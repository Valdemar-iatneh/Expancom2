package com.example.expancom2.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Category
import com.example.expancom2.databinding.DeleteDialogBinding
import com.example.expancom2.databinding.SpinnerCategoryItemBinding
import java.util.Locale

class CategorySpinnerAdapter(context: Context, categoryList: List<Category>) :
    ArrayAdapter<Category> (context, 0, categoryList) {

    private lateinit var binding: SpinnerCategoryItemBinding
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getItem(position: Int): Category? {
        if (position == 0) {
            return null
        }
        return super.getItem(position - 1)
    }

    override fun getCount() = super.getCount() + 1
    override fun isEnabled(position: Int) = position != 0

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        binding = SpinnerCategoryItemBinding.inflate(LayoutInflater.from(context))
        val category = getItem(position)
        val view = binding.root
        if (category != null) {
            binding.categoryName.text = category.name
        }
        return view
    }
}