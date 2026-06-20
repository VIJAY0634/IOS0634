package com.vijay.ios26

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OneHandedKeyboardActivity : AppCompatActivity() {

    private lateinit var checkOff: TextView
    private lateinit var checkLeft: TextView
    private lateinit var checkRight: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_handed_keyboard)

        checkOff = findViewById(R.id.checkOff)
        checkLeft = findViewById(R.id.checkLeft)
        checkRight = findViewById(R.id.checkRight)

        updateChecks()

        findViewById<LinearLayout>(R.id.rowOff)
            .setOnClickListener {
                saveMode("Off")
            }

        findViewById<LinearLayout>(R.id.rowLeft)
            .setOnClickListener {
                saveMode("Left")
            }

        findViewById<LinearLayout>(R.id.rowRight)
            .setOnClickListener {
                saveMode("Right")
            }
    }

    private fun saveMode(mode: String) {

        getSharedPreferences(
            "keyboard_settings",
            MODE_PRIVATE
        ).edit()
            .putString("one_handed_mode", mode)
            .apply()

        updateChecks()

        finish()
    }

    private fun updateChecks() {

        val mode =
            getSharedPreferences(
                "keyboard_settings",
                MODE_PRIVATE
            ).getString(
                "one_handed_mode",
                "Off"
            )

        checkOff.visibility = View.GONE
        checkLeft.visibility = View.GONE
        checkRight.visibility = View.GONE

        when (mode) {
            "Off" -> checkOff.visibility = View.VISIBLE
            "Left" -> checkLeft.visibility = View.VISIBLE
            "Right" -> checkRight.visibility = View.VISIBLE
        }
    }
}