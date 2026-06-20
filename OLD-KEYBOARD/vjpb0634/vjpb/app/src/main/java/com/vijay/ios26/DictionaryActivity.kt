package com.vijay.ios26

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DictionaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        findViewById<ImageButton>(R.id.btnBack)
            .setOnClickListener {
                finish()
            }

        val recycler =
            findViewById<RecyclerView>(
                R.id.recyclerDictionary
            )

        recycler.layoutManager =
            LinearLayoutManager(this)
            recycler.itemAnimator = null
recycler.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val dictionaries = mutableListOf(

            DictionaryItem(
                "English (UK)",
                "Oxford Dictionary of English",
                true
            ),

            DictionaryItem(
                "English (UK)",
                "Oxford Thesaurus of English",
                true
            ),

            DictionaryItem(
                "Apple Dictionary",
                "",
                true
            ),

            DictionaryItem(
                "Arabic-English",
                "Oxford Arabic Dictionary"
            ),

            DictionaryItem(
                "Assamese-English",
                "Oxford Assamese Dictionaries"
            ),

            DictionaryItem(
                "Bangla-English",
                "Oxford Bengali Dictionaries"
            ),

            DictionaryItem(
                "Gujarati-English",
                "Oxford Gujarati Dictionaries"
            ),

            DictionaryItem(
                "Hindi",
                "Rajpal Hindi Dictionary"
            ),

            DictionaryItem(
                "Hindi-English",
                "Oxford Hindi Dictionaries"
            ),

            DictionaryItem(
                "Punjabi-English",
                "Oxford Punjabi Dictionaries"
            ),

            DictionaryItem(
                "Tamil-English",
                "Oxford Tamil Dictionaries"
            ),

            DictionaryItem(
                "Telugu-English",
                "Oxford Telugu Dictionaries"
            ),

            DictionaryItem(
                "Urdu-English",
                "Oxford Urdu Dictionaries"
            ),

            DictionaryItem(
                "Chinese Simplified",
                "Chinese Dictionary"
            ),

            DictionaryItem(
                "Japanese",
                "Japanese Dictionary"
            ),

            DictionaryItem(
                "Korean",
                "Korean Dictionary"
            ),

            DictionaryItem(
                "French",
                "French Dictionary"
            ),

            DictionaryItem(
                "German",
                "German Dictionary"
            ),

            DictionaryItem(
                "Spanish",
                "Spanish Dictionary"
            ),

            DictionaryItem(
                "Italian",
                "Italian Dictionary"
            ),

            DictionaryItem(
                "Portuguese",
                "Portuguese Dictionary"
            ),

            DictionaryItem(
                "Russian",
                "Russian Dictionary"
            ),

            DictionaryItem(
                "Turkish",
                "Turkish Dictionary"
            ),

            DictionaryItem(
                "Vietnamese",
                "Vietnamese Dictionary"
            )
        )

        recycler.adapter =
            DictionaryAdapter(dictionaries)
    }
}