package com.vijay.ios26

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.activity_main)

        
      val general = findViewById<LinearLayout>(R.id.menuGeneral)
val display = findViewById<LinearLayout>(R.id.menuDisplay)
val sounds = findViewById<LinearLayout>(R.id.menuSounds)

general.setOnClickListener {
    startActivity(Intent(this, GeneralActivity::class.java))
}

display.setOnClickListener {
    startActivity(Intent(this, DisplayActivity::class.java))
}

sounds.setOnClickListener {
    startActivity(Intent(this, SoundsActivity::class.java))
}  

        
    }
}
