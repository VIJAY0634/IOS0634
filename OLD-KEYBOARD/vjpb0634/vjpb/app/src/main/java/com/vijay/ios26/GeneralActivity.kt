package com.vijay.ios26 

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class GeneralActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_general)
        
        
        findViewById<ImageButton>(R.id.btnBack)
    .setOnClickListener {
        finish()
    }
        
        val autofill = findViewById<LinearLayout>(R.id.menuAutofill)

 autofill.setOnClickListener {
    startActivity(
        Intent(
            this,
            AutoFillPasswordsActivity::class.java
        )
    )
}

        val keyboard = findViewById<LinearLayout>(R.id.menuKeyboard)

        keyboard.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    KeyboardActivity::class.java
                )
            )
        }
        
        val dictionary =
    findViewById<LinearLayout>(R.id.menuDictionary)

dictionary.setOnClickListener {
    startActivity(
        Intent(
            this,
            DictionaryActivity::class.java
        )
    )
}

}

}