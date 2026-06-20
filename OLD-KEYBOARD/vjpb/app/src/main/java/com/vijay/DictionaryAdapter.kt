package com.vijay.ios26

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView

class DictionaryAdapter(
    private val items: MutableList<DictionaryItem>
) : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView =
            view.findViewById(R.id.title)

        val subtitle: TextView =
            view.findViewById(R.id.subtitle)

        val checkmark: ImageView =
            view.findViewById(R.id.checkmark)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_dictionary,
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

    holder.title.text = item.title
    holder.subtitle.text = item.subtitle

    holder.checkmark.visibility =
        if (item.isEnabled)
            View.VISIBLE
        else
            View.INVISIBLE
}   

 
    override fun getItemCount(): Int {
        return items.size
    }
}