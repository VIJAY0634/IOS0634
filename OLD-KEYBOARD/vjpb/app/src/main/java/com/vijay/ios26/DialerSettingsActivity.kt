package com.vijay.ios26

import android.os.Bundle
import android.widget.NumberPicker
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DialerSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_dialer_settings
        )

        val hourPicker =
            findViewById<NumberPicker>(
                R.id.hourPicker
            )

        val minutePicker =
            findViewById<NumberPicker>(
                R.id.minutePicker
            )

        val amPmPicker =
            findViewById<NumberPicker>(
                R.id.amPmPicker
            )

        hourPicker.minValue = 1
        hourPicker.maxValue = 12
        hourPicker.value = 12

        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.value = 4

        amPmPicker.minValue = 0
        amPmPicker.maxValue = 1

        amPmPicker.displayedValues =
            arrayOf("AM", "PM")
            
            val btnCancel =
    findViewById<ImageButton>(
        R.id.btnCancel
    )

val btnDone =
    findViewById<ImageButton>(
        R.id.btnDone
    )

btnCancel.setOnClickListener {

    finish()
}

btnDone.setOnClickListener {

    Toast.makeText(
        this,
        "Saved",
        Toast.LENGTH_SHORT
    ).show()
}
            
       
            
            
    }
}