package com.araltiparmak.diction.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.araltiparmak.diction.R
import com.araltiparmak.diction.dto.Button
import kotlinx.android.synthetic.main.twister_list_item.view.*

class RecyclerViewAdapter(
    private val width: Int,
    private val dataSet: List<Button>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<TwisterListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwisterListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.twister_list_item, parent, false)
        return TwisterListViewHolder(view, width)
    }

    override fun onBindViewHolder(holderTwisterList: TwisterListViewHolder, position: Int) {
        holderTwisterList.bind(dataSet[position], clickListener)
    }

    override fun getItemCount() = dataSet.size
}

class TwisterListViewHolder(private val view: View, private val width: Int) :
    RecyclerView.ViewHolder(view) {
    fun bind(b: Button, clickListener: (String) -> Unit) {

        val button = view.twisterTextButton
        button.text = b.label
        button.height = width
        itemView.setOnClickListener { clickListener(b.key) }
    }
}