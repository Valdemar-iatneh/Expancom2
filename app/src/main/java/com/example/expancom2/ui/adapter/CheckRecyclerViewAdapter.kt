package com.example.expancom2.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.expancom2.R
import com.example.expancom2.data.roomdb.entity.Check
import java.util.*

class CheckRecyclerViewAdapter(private val checkList: List<Check>) :
    RecyclerView.Adapter<CheckRecyclerViewAdapter.CheckViewHolder>() {

    class CheckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkItemView: TextView = itemView.findViewById(R.id.textView)
        val circleText : TextView = itemView.findViewById(R.id.tc_circle)
        val circle : CardView = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return CheckViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CheckViewHolder, position: Int) {
        val current = checkList[position]
        holder.checkItemView.text = current.name
        holder.circleText.text = holder.checkItemView.text.substring(0,1).capitalize()
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.circle.setBackgroundColor(color)
    }

    override fun getItemCount() = checkList.size
}