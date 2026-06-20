package com.vijay.ios26

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val liquidGlass =
            findViewById<LinearLayout>(R.id.liquidGlassOption)

        liquidGlass.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LiquidGlassSettingsActivity::class.java
                )
            )
        }
    }
}