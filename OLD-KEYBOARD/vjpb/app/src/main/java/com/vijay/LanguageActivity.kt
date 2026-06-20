package com.vijay.ios26

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        findViewById<ImageButton>(R.id.btnClose)
    .setOnClickListener {
        finish()
    }

        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerLanguages)

        val languages = listOf(
            "English",
            "Hindi",
            "Arabic",
            "Bengali",
            "Chinese",
            "French",
            "German",
            "Gujarati",
            "Italian",
            "Japanese",
            "Kannada",
            "Korean",
            "Malayalam",
            "Marathi",
            "Portuguese",
            "Punjabi",
            "Russian",
            "Spanish",
            "Tamil",
            "Telugu",
            "Thai",
            "Turkish",
            "Urdu",
            "Vietnamese"
        )

        recyclerView.layoutManager =
            LinearLayoutManager(this)
val divider =
    DividerItemDecoration(
        this,
        DividerItemDecoration.VERTICAL
    )

divider.setDrawable(
    getDrawable(R.drawable.divider)!!
)

recyclerView.addItemDecoration(divider)


        recyclerView.adapter =
    LanguageAdapter(
        this,
        languages
    )
    }
}