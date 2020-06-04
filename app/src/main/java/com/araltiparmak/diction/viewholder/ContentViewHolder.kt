package com.araltiparmak.diction.viewholder

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.araltiparmak.diction.R
import kotlinx.android.synthetic.main.content_card.view.*

class ContentViewHolder constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    constructor(parent: ViewGroup) :
            this(LayoutInflater.from(parent.context).inflate(R.layout.content_card, parent, false))

    fun bind(exercise: String) {
        itemView.content.text = exercise
        itemView.content.movementMethod = ScrollingMovementMethod()
    }
}