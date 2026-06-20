package com.vijay.ios26

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AutoFillPasswordsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autofill_passwords)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
