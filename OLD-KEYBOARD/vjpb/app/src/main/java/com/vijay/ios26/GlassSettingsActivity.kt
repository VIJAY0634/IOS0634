package com.vijay.ios26

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GlassSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glass_settings)

        val prefs =
            getSharedPreferences(
                "keyboard_settings",
                MODE_PRIVATE
            )

        val blurSeek =
            findViewById<SeekBar>(R.id.seekBlur)

        val opacitySeek =
            findViewById<SeekBar>(R.id.seekOpacity)

        val blurValue =
            findViewById<TextView>(R.id.txtBlur)

        val opacityValue =
            findViewById<TextView>(R.id.txtOpacity)

        blurSeek.progress =
            prefs.getInt("glass_blur", 20)

        opacitySeek.progress =
            prefs.getInt("glass_opacity", 85)

        blurValue.text =
            "${blurSeek.progress}%"

        opacityValue.text =
            "${opacitySeek.progress}%"

        blurSeek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    blurValue.text = "$progress%"

                    prefs.edit()
                        .putInt(
                            "glass_blur",
                            progress
                        )
                        .apply()
                }

                override fun onStartTrackingTouch(
                    seekBar: SeekBar?
                ) {}

                override fun onStopTrackingTouch(
                    seekBar: SeekBar?
                ) {}
            }
        )

        opacitySeek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    opacityValue.text = "$progress%"

                    prefs.edit()
                        .putInt(
                            "glass_opacity",
                            progress
                        )
                        .apply()
                }

                override fun onStartTrackingTouch(
                    seekBar: SeekBar?
                ) {}

                override fun onStopTrackingTouch(
                    seekBar: SeekBar?
                ) {}
            }
        )
    }
}