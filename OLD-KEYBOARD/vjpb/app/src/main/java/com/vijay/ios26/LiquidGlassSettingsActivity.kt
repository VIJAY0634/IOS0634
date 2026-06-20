package com.vijay.ios26


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class LiquidGlassSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liquid_glass)
        

        val preview = findViewById<CardView>(R.id.previewCard)
val wallpaper = findViewById<ImageView>(R.id.wallpaper)

val clearMode = findViewById<LinearLayout>(R.id.clearMode)
val tintedMode = findViewById<LinearLayout>(R.id.tintedMode)

val checkMark = findViewById<TextView>(R.id.checkMark)
val checkMarkClear = findViewById<TextView>(R.id.checkMarkClear)

checkMarkClear.visibility = View.GONE
checkMark.visibility = View.VISIBLE

checkMarkClear.visibility = View.GONE
checkMark.visibility = View.VISIBLE

clearMode.setOnClickListener {

    wallpaper.setImageResource(R.drawable.liquid_glass_wallpaper_clear)

    checkMarkClear.visibility = View.VISIBLE
    checkMark.visibility = View.GONE
}

tintedMode.setOnClickListener {

    wallpaper.setImageResource(R.drawable.liquid_glass_wallpaper)

    checkMarkClear.visibility = View.GONE
    checkMark.visibility = View.VISIBLE
}
    }
}