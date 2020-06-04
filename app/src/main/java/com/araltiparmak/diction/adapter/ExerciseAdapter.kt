package com.araltiparmak.diction.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.araltiparmak.diction.viewholder.ContentViewHolder

class ExerciseAdapter : RecyclerView.Adapter<ContentViewHolder>() {
    private var list: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}