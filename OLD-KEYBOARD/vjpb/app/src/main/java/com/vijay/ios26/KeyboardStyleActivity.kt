package com.vijay.ios26

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KeyboardStyleActivity : AppCompatActivity() {

   
   private lateinit var checkIos18Light: TextView
private lateinit var checkIos18Dark: TextView

private lateinit var checkLiquidGlass: TextView
private lateinit var checkNative: TextView
private lateinit var checkSolid: TextView
   
   
   
   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard_style)

        val prefs =
            getSharedPreferences(
                "keyboard_settings",
                MODE_PRIVATE
            )

checkIos18Light =
    findViewById(R.id.checkIos18Light)

checkIos18Dark =
    findViewById(R.id.checkIos18Dark)


        checkLiquidGlass =
            findViewById(R.id.checkLiquidGlass)

        checkNative =
            findViewById(R.id.checkNative)

        checkSolid =
            findViewById(R.id.checkSolid)

        val version =
    prefs.getString(
        "keyboard_version",
        "ios26"
    )

val theme =
    prefs.getString(
        "theme_mode",
        "light"
    )

val currentStyle =
    prefs.getString(
        "keyboard_style",
        "Native"
    )

if (version == "ios18") {

    if (theme == "dark") {
        updateChecks("iOS18Dark")
    } else {
        updateChecks("iOS18Light")
    }

} else {

    updateChecks(currentStyle ?: "Native")
}
        
        findViewById<LinearLayout>(R.id.rowIos18Light)
    .setOnClickListener {

        prefs.edit()
            .putString(
                "keyboard_version",
                "ios18"
            )
            .putString(
                "theme_mode",
                "light"
            )
            .apply()

        updateChecks("iOS18Light")
    }

findViewById<LinearLayout>(R.id.rowIos18Dark)
    .setOnClickListener {

        prefs.edit()
            .putString(
                "keyboard_version",
                "ios18"
            )
            .putString(
                "theme_mode",
                "dark"
            )
            .apply()

        updateChecks("iOS18Dark")
    }

        findViewById<LinearLayout>(R.id.rowLiquidGlass)
            .setOnClickListener {

                prefs.edit()
                    .putString(
    "keyboard_style",
    "Liquid Glass"
)
.putString(
    "keyboard_version",
    "ios26"
)
                    .apply()
                    
                    
prefs.edit()
    .putString(
        "theme_mode",
        "light"
    )
    .apply()
                    

                updateChecks("Liquid Glass")
            }

      findViewById<LinearLayout>(R.id.rowNative)
    .setOnClickListener {

        prefs.edit()
            .putString(
                "keyboard_style",
                "Native"
            )
            .putString(
                "keyboard_version",
                "ios26"
            )
            .apply()
            
            
prefs.edit()
    .putString(
        "theme_mode",
        "light"
    )
    .apply()


        updateChecks("Native")
    }

        findViewById<LinearLayout>(R.id.rowSolid)
            .setOnClickListener {

                prefs.edit()
                  .putString(
    "keyboard_style",
    "Solid"
)
.putString(
    "keyboard_version",
    "ios26"
) 
                    .apply()
                    
                    
                    prefs.edit()
    .putString(
        "theme_mode",
        "light"
    )
    .apply()

                updateChecks("Solid")
            }
    }

    
    private fun updateChecks(style: String) {

    checkIos18Light.visibility = View.GONE
    checkIos18Dark.visibility = View.GONE

    checkLiquidGlass.visibility = View.GONE
    checkNative.visibility = View.GONE
    checkSolid.visibility = View.GONE

    when (style) {

        "iOS18Light" ->
            checkIos18Light.visibility =
                View.VISIBLE

        "iOS18Dark" ->
            checkIos18Dark.visibility =
                View.VISIBLE

        "Liquid Glass" ->
            checkLiquidGlass.visibility =
                View.VISIBLE

        "Native" ->
            checkNative.visibility =
                View.VISIBLE

        "Solid" ->
            checkSolid.visibility =
                View.VISIBLE
    }
}
    
    
}