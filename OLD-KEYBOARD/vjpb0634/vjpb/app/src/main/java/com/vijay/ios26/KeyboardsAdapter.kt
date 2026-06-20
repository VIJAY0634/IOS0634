package com.vijay.ios26

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KeyboardsAdapter(
    private val keyboards: MutableList<String>
) : RecyclerView.Adapter<KeyboardsAdapter.ViewHolder>() {

    private var editMode = false

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val txtKeyboardName: TextView =
            view.findViewById(R.id.txtKeyboardName)

        val btnDelete: ImageButton =
            view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_keyboard,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.txtKeyboardName.text =
            keyboards[position]

        holder.btnDelete.visibility =
            if (editMode)
                View.VISIBLE
            else
                View.GONE

        holder.btnDelete.setOnClickListener {

            keyboards.removeAt(position)

            notifyItemRemoved(position)

            val prefs = holder.itemView.context
                .getSharedPreferences(
                    "keyboard_settings",
                    Context.MODE_PRIVATE
                )

            prefs.edit()
                .putStringSet(
                    "selected_languages",
                    keyboards.toSet()
                )
                .apply()
        }
    }

    override fun getItemCount(): Int {
        return keyboards.size
    }

    fun setEditMode(enabled: Boolean) {
        editMode = enabled
        notifyDataSetChanged()
    }
}