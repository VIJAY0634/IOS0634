package com.vijay.ios26

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapter(
private val context: Context,
private var languages: List<String>
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

private val prefs =
    context.getSharedPreferences(
        "keyboard_settings",
        Context.MODE_PRIVATE
    )

private var selectedLanguages =
    prefs.getStringSet(
        "selected_languages",
        mutableSetOf("English")
    )?.toMutableSet()
        ?: mutableSetOf("English")

class LanguageViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    val txtLanguage: TextView =
        itemView.findViewById(R.id.txtLanguage)

    val checkMark: TextView =
        itemView.findViewById(R.id.checkMark)
}

override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
): LanguageViewHolder {

    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_language, parent, false)

    return LanguageViewHolder(view)
}

override fun onBindViewHolder(
    holder: LanguageViewHolder,
    position: Int
) {

    val language = languages[position]

    holder.txtLanguage.text = language

    holder.checkMark.visibility =
        if (selectedLanguages.contains(language))
            View.VISIBLE
        else
            View.GONE

    holder.itemView.setOnClickListener {

        if (selectedLanguages.contains(language))
            selectedLanguages.remove(language)
        else
            selectedLanguages.add(language)

        prefs.edit()
            .putStringSet(
                "selected_languages",
                selectedLanguages
            )
            .apply()

        notifyDataSetChanged()
    }
}

override fun getItemCount(): Int {
    return languages.size
}

fun updateList(newList: List<String>) {
    languages = newList
    notifyDataSetChanged()
}

}