package com.vijay.ios26

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KeyboardActivity : AppCompatActivity() {

    private lateinit var txtOneHandedValue: TextView
    private lateinit var txtKeyboardStyle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard)
        
        val popupStyleRow =
    findViewById<LinearLayout>(R.id.popupStyleRow)

val txtPopupStyle =
    findViewById<TextView>(R.id.txtPopupStyle)
     
     popupStyleRow.setOnClickListener {

    val styles = arrayOf(
        "iOS 26",
        "iOS 18",
        "Android"
    )

    AlertDialog.Builder(this)
        .setTitle("Popup Style")
        .setItems(styles) { _, which ->
        val prefs = getSharedPreferences("keyboard_settings", MODE_PRIVATE)

prefs.edit()
    .putInt("popup_style", which)
    .apply()

            txtPopupStyle.text = styles[which]

            val value = when (which) {
                0 -> "ios26"
                1 -> "old_ios"
                else -> "android"
            }

            getSharedPreferences(
                "keyboard_settings",
                MODE_PRIVATE
            ).edit()
                .putString("popup_style", value)
                .apply()
        }
        .show()
}

        val prefs =
            getSharedPreferences(
                "keyboard_settings",
                MODE_PRIVATE
            )

        txtKeyboardStyle =
            findViewById(R.id.txtKeyboardStyle)

        txtOneHandedValue =
            findViewById(R.id.txtOneHandedValue)

        txtKeyboardStyle.text =
            prefs.getString(
                "keyboard_style",
                "Native"
            )

        findViewById<LinearLayout>(R.id.keyboardStyleRow)
            .setOnClickListener {
                startActivity(
                    Intent(
                        this,
                        KeyboardStyleActivity::class.java
                    )
                )
            }


findViewById<LinearLayout>(R.id.glassSettingsRow)
    .setOnClickListener {

        startActivity(
            Intent(
                this,
                GlassSettingsActivity::class.java
            )
        )
    }
    
        findViewById<LinearLayout>(R.id.keyboardsRow)
            .setOnClickListener {
                startActivity(
                    Intent(
                        this,
                        KeyboardsListActivity::class.java
                    )
                )
            }

        findViewById<LinearLayout>(R.id.textReplacementRow)
            .setOnClickListener {
                startActivity(
                    Intent(
                        this,
                        TextReplacementActivity::class.java
                    )
                )
            }

        findViewById<LinearLayout>(R.id.oneHandedRow)
            .setOnClickListener {
                startActivity(
                    Intent(
                        this,
                        OneHandedKeyboardActivity::class.java
                    )
                )
            }
    }

    override fun onResume() {
        super.onResume()

        val prefs =
            getSharedPreferences(
                "keyboard_settings",
                MODE_PRIVATE
            )

        txtOneHandedValue.text =
            prefs.getString(
                "one_handed_mode",
                "Off"
            )

        txtKeyboardStyle.text =
            prefs.getString(
                "keyboard_style",
                "Native"
            )
    }
}