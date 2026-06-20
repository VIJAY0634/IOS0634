package com.vijay.ios26

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.cardview.widget.CardView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class KeyboardsListActivity : AppCompatActivity() {
private lateinit var adapter: KeyboardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_keyboards_list)
    val prefs =
    getSharedPreferences(
        "keyboard_settings",
        MODE_PRIVATE
    )

    findViewById<ImageButton>(R.id.btnBack)
        .setOnClickListener {
            finish()
        }
        
       val selectedLanguages =
    prefs.getStringSet(
        "selected_languages",
        mutableSetOf("English")
    )?.toMutableList()
        ?: mutableListOf("English")

val recycler =
    findViewById<RecyclerView>(
        R.id.recyclerKeyboards
    )

recycler.layoutManager =
    LinearLayoutManager(this)

adapter =
    KeyboardsAdapter(selectedLanguages)

recycler.adapter = adapter
        
        
    
    var editMode = false

findViewById<Button>(R.id.btnEdit)
    .setOnClickListener {

        editMode = !editMode

        adapter.setEditMode(editMode)

        if (editMode)
            (it as Button).text = "Done"
        else
            (it as Button).text = "Edit"
    }


    findViewById<CardView>(R.id.addKeyboardRow)
        .setOnClickListener {

            startActivity(
                Intent(
                    this,
                    LanguageActivity::class.java
                )
            )
        }
}
}