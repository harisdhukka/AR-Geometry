package com.example.myapplication.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Solid
import com.example.myapplication.utils.ColorPicker

class SolidItemAdapter(val context: Context, private val dataset: List<Solid>) :
    RecyclerView.Adapter<SolidItemAdapter.SolidItemViewHolder>() {
    var onItemClick: ((Solid) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolidItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false)
        return SolidItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SolidItemViewHolder, position: Int) {
        holder.textViewTitle.text = dataset[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(dataset[position].imageResourceId)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class SolidItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataset[adapterPosition])
            }
        }
    }
}