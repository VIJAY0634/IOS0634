package com.vijay.ios26

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextReplacementAdapter(
    private val items: MutableList<TextReplacement>
) : RecyclerView.Adapter<TextReplacementAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val txtPhrase: TextView =
            view.findViewById(R.id.txtPhrase)

        val txtShortcut: TextView =
            view.findViewById(R.id.txtShortcut)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_text_replacement,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.txtPhrase.text =
            item.phrase

        holder.txtShortcut.text =
            item.shortcut
    }

    override fun getItemCount(): Int {
        return items.size
    }
}