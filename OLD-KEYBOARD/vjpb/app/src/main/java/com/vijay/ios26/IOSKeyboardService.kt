    package com.vijay.ios26

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.graphics.Color
import android.widget.LinearLayout
import android.view.Gravity
import android.text.InputType
import android.view.MotionEvent
import android.view.inputmethod.ExtractedTextRequest
import android.widget.TextView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow


class IOSKeyboardService : InputMethodService() {

     private var capitalizeNext = true
     private var isUpperCase = true
     private var capsLock = false
     private var lastShiftTapTime = 0L
     
     private var popupStyle = 0
     
     private var keyboardVersion = "ios26"
     private var themeMode = "light"
     
     private var lastSpaceTime = 0L
     private lateinit var micButton: ImageButton
    private var manualNumbers = false
    private var currentLanguage = "en_US"
    private var trackpadMode = false
private var lastX = 0f
   
   private fun setKeysVisible(view: View, visible: Boolean) {

    val keys = listOf(
    
     //letters
        R.id.q,R.id.w,R.id.e,R.id.r,R.id.t,
        R.id.y,R.id.u,R.id.i,R.id.o,R.id.p,
        R.id.a,R.id.s,R.id.d,R.id.f,R.id.g,
        R.id.h,R.id.j,R.id.k,R.id.l,
        R.id.z,R.id.x,R.id.c,R.id.v,
        R.id.b,R.id.n,R.id.m,
        
        
        // special keys
    R.id.shift,
    R.id.key123,
    R.id.backspace,
    R.id.returnKey,
    )

    for (id in keys) {

        view.findViewById<Button?>(id)?.let { btn ->

            if (visible) {
                btn.text = btn.tag?.toString() ?: ""
                btn.setTextColor(Color.BLACK)
            } else {
                btn.setTextColor(Color.TRANSPARENT)
}
            }
        }
    }

   
   
    

    override fun onCreateInputView(): View {
		
        
        
	

      val inputType =
    currentInputEditorInfo?.inputType
        ?: InputType.TYPE_CLASS_TEXT

val layoutId = when {

    manualNumbers ->
        R.layout.symbols_view

    (inputType and InputType.TYPE_CLASS_NUMBER) != 0 ->
        R.layout.numbers_view

    (inputType and InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) != 0 ->
        R.layout.email_view

    (inputType and InputType.TYPE_TEXT_VARIATION_URI) != 0 ->
        R.layout.url_view

    else ->
        R.layout.keyboard_view
}

val view = layoutInflater.inflate(layoutId, null)


updateLetterKeys(view)


val nums = listOf(
    "num0" to "0",
    "num1" to "1",
    "num2" to "2",
    "num3" to "3",
    "num4" to "4",
    "num5" to "5",
    "num6" to "6",
    "num7" to "7",
    "num8" to "8",
    "num9" to "9"
)

nums.forEach { (idName, value) ->

    val id = resources.getIdentifier(
        idName,
        "id",
        packageName
    )

    view.findViewById<Button?>(id)
        ?.setOnClickListener {

            currentInputConnection
                .commitText(value, 1)
        }
}


val shiftButton =
    view.findViewById<Button?>(R.id.shift)
    
    shiftButton?.setOnClickListener {

    val now = System.currentTimeMillis()

    if (now - lastShiftTapTime < 400) {

        capsLock = true
        isUpperCase = true

    } else {

        if (capsLock) {

            capsLock = false
            isUpperCase = false

        } else {

            isUpperCase = !isUpperCase
        }
    }

    lastShiftTapTime = now

    updateLetterKeys(view)
}


applyKeyboardStyle(view)

applyThemeToKeys(view)


listOf(
    R.id.shift,
    R.id.key123,
    R.id.backspace,
    R.id.returnKey
).forEach { id ->

    view.findViewById<Button?>(id)?.let {
        it.tag = it.text
    }
}


view.findViewById<Button?>(R.id.key123)?.setOnClickListener {

    manualNumbers = true

    setInputView(onCreateInputView())
}

view.findViewById<Button?>(R.id.abc)?.setOnClickListener {

    manualNumbers = false

    setInputView(onCreateInputView())
}

view.findViewById<Button?>(R.id.keySymbols)?.setOnClickListener {

    manualNumbers = true

    setInputView(onCreateInputView())
}





view.findViewById<ImageButton?>(R.id.mic)?.let {
    micButton = it
}

val globe = view.findViewById<ImageButton?>(R.id.globe)

globe?.setOnClickListener {

    showKeyboardMenu(globe)

}

globe?.setOnLongClickListener {
 showLanguagePopup(it)

    true
}

view.findViewById<ImageButton?>(R.id.mic)?.setOnClickListener {

micButton.animate()
    .scaleX(1.15f)
    .scaleY(1.15f)
    .setDuration(120)
    .withEndAction {
        micButton.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(120)
            .start()
    }
    .start()



    // voice input later
}
val suggestion1 = view.findViewById<TextView?>(R.id.suggestion1)
val suggestion2 = view.findViewById<TextView?>(R.id.suggestion2)
val suggestion3 = view.findViewById<TextView?>(R.id.suggestion3)
val btnSuggestionMenu =
    view.findViewById<ImageButton?>(R.id.btnSuggestionMenu)
	
suggestion1?.setOnClickListener {

    currentInputConnection.deleteSurroundingText(20, 0)

    currentInputConnection.commitText(
        suggestion1?.text.toString() + " ",
        1
    )
}

suggestion2?.setOnClickListener {

    currentInputConnection.deleteSurroundingText(20, 0)

    currentInputConnection.commitText(
        suggestion2?.text.toString() + " ",
        1
    )
}

suggestion3?.setOnClickListener {

    currentInputConnection.deleteSurroundingText(20, 0)

    currentInputConnection.commitText(
        suggestion3?.text.toString() + " ",
        1
    )
}

var menuOpen = false

btnSuggestionMenu?.setOnClickListener {

    menuOpen = !menuOpen

    if(menuOpen){

        suggestion1?.text = "More"
        suggestion2?.text = "Format"
        suggestion3?.text = "AI"

    }else{

        suggestion1?.text = "I"
        suggestion2?.text = "The"
        suggestion3?.text = "I'm"
    }
}

        val keys = listOf(
            "Q","W","E","R","T","Y","U","I","O","P",
            "A","S","D","F","G","H","J","K","L",
            "Z","X","C","V","B","N","M"
        )

        for (key in keys) {

            val id = resources.getIdentifier(
                key.lowercase(),
                "id",
                packageName
            )

            val button = view.findViewById<Button>(id)
            button?.tag = button?.text
            button?.setOnTouchListener { _, event ->

    if (!trackpadMode)
        return@setOnTouchListener false

    when (event.action) {

        MotionEvent.ACTION_DOWN -> {
            lastX = event.rawX
        }

        MotionEvent.ACTION_MOVE -> {

            val delta = event.rawX - lastX

            val extracted =
                currentInputConnection.getExtractedText(
                    android.view.inputmethod.ExtractedTextRequest(),
                    0
                )

            val pos = extracted?.selectionStart ?: 0

            if (delta > 15) {

                currentInputConnection.setSelection(
                    pos + 1,
                    pos + 1
                )

                lastX = event.rawX

            } else if (delta < -15) {

                val newPos = maxOf(0, pos - 1)

                currentInputConnection.setSelection(
                    newPos,
                    newPos
                )

                lastX = event.rawX
            }
        }
    }

    true
}
            

button?.setOnClickListener {

    val letter =
    if (isUpperCase)
        key.uppercase()
    else
        key.lowercase()



val popupView =
    layoutInflater.inflate(
        R.layout.popup_key,
        null
    )

val popupText =
    popupView.findViewById<TextView>(
        R.id.popupLetter
    )

popupText.text = letter

if (keyboardVersion == "ios18") {
    popupText.setBackgroundResource(
        R.drawable.popup_ios18
    )
} else {
    popupText.setBackgroundResource(
        R.drawable.popup_ios26
    )
}

val popupWindow = PopupWindow(
    popupView,
    LinearLayout.LayoutParams.WRAP_CONTENT,
    LinearLayout.LayoutParams.WRAP_CONTENT,
    false
)

popupWindow.showAsDropDown(
    button,
    0,
    -170,
    Gravity.CENTER
)

button.postDelayed({

    popupWindow.dismiss()

}, 120)
  

    currentInputConnection.commitText(letter, 1)

    if (!capsLock) {

    isUpperCase = false

    updateLetterKeys(view)
}

    val textBefore =
        currentInputConnection
            .getTextBeforeCursor(20, 0)
            ?.toString()
            ?.lowercase()
            ?: ""

    val currentWord =
        textBefore
            .split(" ")
            .lastOrNull()
            ?: ""

    when {

        currentWord.startsWith("im") -> {
            suggestion1?.text = "I'm"
            suggestion2?.text = "Important"
            suggestion3?.text = "Impossible"
        }

        currentWord.startsWith("hel") -> {
            suggestion1?.text = "Hello"
            suggestion2?.text = "Help"
            suggestion3?.text = "Held"
        }

        currentWord.startsWith("goo") -> {
            suggestion1?.text = "Good"
            suggestion2?.text = "Google"
            suggestion3?.text = "Goodbye"
        }

        else -> {
            suggestion1?.text = "The"
            suggestion2?.text = "I'm"
            suggestion3?.text = "You"
        }
    }
}

}

val symbolKeys = listOf(
    "sym1" to "[",
    "sym2" to "]",
    "sym3" to "{",
    "sym4" to "}",
    "sym5" to "#",
    "sym6" to "%",
    "sym7" to "^",
    "sym8" to "*",
    "sym9" to "+",
    "sym10" to "=",

    "sym11" to "_",
    "sym12" to "\\",
    "sym13" to "|",
    "sym14" to "~",
    "sym15" to "<",
    "sym16" to ">",
    "sym17" to "€",
    "sym18" to "£",
    "sym19" to "¥",
    "sym20" to "•",

    "sym21" to ".",
    "sym22" to ",",
    "sym23" to "?",
    "sym24" to "!",
    "sym25" to "'"
)

symbolKeys.forEach { (idName, value) ->

    val id = resources.getIdentifier(
        idName,
        "id",
        packageName
    )

    view.findViewById<Button?>(id)?.setOnClickListener {

        currentInputConnection.commitText(
            value,
            1
        )
    }
}



 
view.findViewById<Button>(R.id.backspace)?.setOnClickListener {
    currentInputConnection.deleteSurroundingText(1, 0)
}


view.findViewById<Button>(R.id.returnKey)?.setOnClickListener {
    currentInputConnection.commitText("\n", 1)
}


val spaceButton = view.findViewById<Button?>(R.id.space)

spaceButton?.setOnLongClickListener {

    trackpadMode = true
    

    spaceButton.alpha = 1f
    
    setKeysVisible(view, false)
    
    true
}

spaceButton?.setOnTouchListener { _, event ->

    if (!trackpadMode) {
        return@setOnTouchListener false
    }
    spaceButton?.setOnClickListener {

    if (!trackpadMode) {

        currentInputConnection.commitText(" ", 1)
    }
}

    when (event.action) {

        MotionEvent.ACTION_DOWN -> {
            lastX = event.x
        }

MotionEvent.ACTION_MOVE -> {

    val delta = event.x - lastX

    val extracted =
        currentInputConnection.getExtractedText(
            android.view.inputmethod.ExtractedTextRequest(),
            0
        )

    val pos = extracted?.selectionStart ?: 0

    if (delta > 15) {

        currentInputConnection.setSelection(
            pos + 1,
            pos + 1
        )

        lastX = event.x

    } else if (delta < -15) {

        val newPos = maxOf(0, pos - 1)

        currentInputConnection.setSelection(
            newPos,
            newPos
        )

        lastX = event.x
    }
}

        MotionEvent.ACTION_UP,
        MotionEvent.ACTION_CANCEL -> {

            
            trackpadMode = false
            
            setKeysVisible(view, true)
            
            spaceButton.alpha = 1f
        }
    }

    true
}


return view
}


private fun updateLetterKeys(view: View) {

    val keys = listOf(
        "q","w","e","r","t",
        "y","u","i","o","p",
        "a","s","d","f","g",
        "h","j","k","l",
        "z","x","c","v",
        "b","n","m"
    )

    keys.forEach { key ->

        val id = resources.getIdentifier(
            key,
            "id",
            packageName
        )

        val btn = view.findViewById<Button?>(id)

        btn?.text =
            if (isUpperCase)
                key.uppercase()
            else
                key.lowercase()
    }

val deleteNum =
    view.findViewById<ImageButton?>(
        R.id.deleteNum
    )

deleteNum?.setOnClickListener {

    currentInputConnection
        .deleteSurroundingText(1, 0)
}

deleteNum?.setOnLongClickListener {

    repeat(20) {

        currentInputConnection
            .deleteSurroundingText(1, 0)
    }

    true
}
view.findViewById<Button?>(
    R.id.decimalKey
)?.setOnClickListener {

    currentInputConnection
        .commitText(".", 1)
}

    val shiftBtn =
        view.findViewById<Button?>(R.id.shift)

    when {

        capsLock -> {

            shiftBtn?.setBackgroundResource(
                R.drawable.shift_caps
            )

            shiftBtn?.text = "⇪"
        }

        isUpperCase -> {

            shiftBtn?.setBackgroundResource(
                R.drawable.shift_on
            )

            shiftBtn?.text = "⇧"
        }

        else -> {

            shiftBtn?.setBackgroundResource(
                R.drawable.shift_off
            )

            shiftBtn?.text = "⇧"
        }
    }
    }
    
 private fun applyThemeToKeys(view: View) {

    val keyIds = listOf(

        R.id.q,R.id.w,R.id.e,R.id.r,R.id.t,
        R.id.y,R.id.u,R.id.i,R.id.o,R.id.p,

        R.id.a,R.id.s,R.id.d,R.id.f,R.id.g,
        R.id.h,R.id.j,R.id.k,R.id.l,

        R.id.z,R.id.x,R.id.c,R.id.v,
        R.id.b,R.id.n,R.id.m,

        R.id.num0,R.id.num1,R.id.num2,
        R.id.num3,R.id.num4,R.id.num5,
        R.id.num6,R.id.num7,R.id.num8,
        R.id.num9,

        R.id.shift,
        R.id.key123,
        R.id.backspace,
        R.id.returnKey,
        R.id.space,
        R.id.decimalKey
    )

    keyIds.forEach { id ->

        val btn =
            view.findViewById<Button?>(id)

        if (themeMode == "dark") {

            btn?.setTextColor(Color.WHITE)

        } else {

            btn?.setTextColor(Color.BLACK)
        }
    }
}   
    
    





private fun showKeyboardMenu(anchor: View) {

    val popupView =
        layoutInflater.inflate(
            R.layout.keyboard_menu,
            null
        )

    val popupWindow = PopupWindow(
        popupView,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        true
    )

    popupWindow.elevation = 20f

    popupWindow.showAsDropDown(
        anchor,
        0,
        -450
    )

    popupView.findViewById<View>(R.id.menuEmoji)
        ?.setOnClickListener {

            currentInputConnection.commitText("😀", 1)
            popupWindow.dismiss()
        }

    popupView.findViewById<View>(R.id.menuClipboard)
        ?.setOnClickListener {
            popupWindow.dismiss()
        }

    popupView.findViewById<View>(R.id.menuSettings)
        ?.setOnClickListener {
            popupWindow.dismiss()
}
}



private fun showLanguagePopup(anchor: View) {

    val popupView =
        layoutInflater.inflate(
            R.layout.language_popup,
            null
        )

    val popupWindow = PopupWindow(
        popupView,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        true
    )

    popupWindow.elevation = 20f

    popupWindow.showAsDropDown(
        anchor,
        0,
        -500
    )

    popupView.findViewById<TextView>(
        R.id.langEnglishUS
    ).setOnClickListener {

        currentLanguage = "en_US"

        popupWindow.dismiss()
    }

    popupView.findViewById<TextView>(
        R.id.langEnglishUK
    ).setOnClickListener {

        currentLanguage = "en_GB"

        popupWindow.dismiss()
    }

    popupView.findViewById<TextView>(
        R.id.langHindi
    ).setOnClickListener {

        currentLanguage = "hi"

        popupWindow.dismiss()
    
}

popupView.findViewById<TextView>(
    R.id.langEmoji
).setOnClickListener {

    currentInputConnection.commitText("😀", 1)

    popupWindow.dismiss()
}

  
  }   // showLanguagePopup ends here

private fun showEmojiPopup(anchor: View) {

    val popupView =
        layoutInflater.inflate(
            R.layout.emoji_popup,
            null
        )

    val popupWindow = PopupWindow(
        popupView,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        true
    )

    popupWindow.elevation = 20f

    popupWindow.showAsDropDown(
        anchor,
        0,
        -500
    )

    val emojiIds = listOf(
        R.id.emoji1,
        R.id.emoji2,
        R.id.emoji3,
        R.id.emoji4,
        R.id.emoji5,
        R.id.emoji6,
        R.id.emoji7,
        R.id.emoji8
    )

    emojiIds.forEach { id ->

        popupView.findViewById<TextView>(id)
            .setOnClickListener {

                currentInputConnection.commitText(
                    (it as TextView).text,
                    1
                )

                popupWindow.dismiss()
            }
    }
}



private fun applyKeyboardStyle(view: View) {



val prefs =
    getSharedPreferences(
        "keyboard_settings",
        MODE_PRIVATE
    )

keyboardVersion =
    prefs.getString(
        "keyboard_version",
        "ios26"
    ) ?: "ios26"

themeMode =
    prefs.getString(
        "theme_mode",
        "light"
    ) ?: "light"



popupStyle =
    prefs.getString(
        "popup_style",
        "0"
    )?.toIntOrNull() ?: 0


    val style =
        prefs.getString(
            "keyboard_style",
            "Native"
        )

    when (keyboardVersion) {

    "ios18" -> {

        if (themeMode == "dark") {

            view.setBackgroundColor(
                Color.parseColor("#1C1C1E")
            )

        } else {

            view.setBackgroundColor(
                Color.parseColor("#D1D5DB")
            )
        }
    }

    "ios26" -> {

        when (style) {

           "Liquid Glass" -> {

    val opacity = 85

    view.alpha =
        0.7f + (opacity / 100f * 0.3f)

    if (themeMode == "dark") {

        view.setBackgroundColor(
            Color.parseColor("#1C1C1E")
        )

    } else {

        view.setBackgroundColor(
            Color.parseColor("#F2F2F7")
        )
    }
}

           "Native" -> {

    view.alpha = 1f

    if (themeMode == "dark") {

        view.setBackgroundColor(
            Color.parseColor("#1C1C1E")
        )

    } else {

        view.setBackgroundColor(
            Color.parseColor("#F2F2F7")
        )
    }
}

           "Solid" -> {

    view.alpha = 1f

    if (themeMode == "dark") {

        view.setBackgroundColor(
            Color.BLACK
        )

    } else {

        view.setBackgroundColor(
            Color.WHITE
        )
      }
    }
   }
 }
}
}
}
        
           
        
	