package com.example.pert7no1

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.pert7no1.AwalActivity
import com.example.pert7no1.R

class NextView (context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val iconX = ImageView(context).apply {
        setImageResource(R.drawable.icon_x)
        layoutParams = LinearLayout.LayoutParams(200, 100).apply {
            setPadding(30, 32, 30, 0)
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }
    private val textUtama = TextView(context).apply {
        text = "Enter your passsword"
        gravity = Gravity.START
        setTypeface(null, Typeface.BOLD)
        textSize = 23f
        setPadding(6, 80, 30, 0)
        setTextColor(resources.getColor(R.color.white, null))
    }
    private val usernameField = EditText(context).apply {
        hint = "Email\nqomariasiti17@gmail.com"
        setHintTextColor(Color.LTGRAY)
        setTextColor(Color.WHITE)
        setHighlightColor(Color.WHITE)
        setPadding(30, 30, 30, 30)
        background = createRoundedBackground()
        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS or InputType.TYPE_CLASS_TEXT
        setLines(2)
        maxLines = 2
        ellipsize = null
        textSize = 13f
    }

    private val passwordField = EditText(context).apply {
        hint = "Password"
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        setHintTextColor(Color.LTGRAY)
        setTextColor(Color.WHITE)
        setHighlightColor(Color.WHITE)
        setPadding(40, 40, 40, 40)
        background = createRoundedBackground2()
    }
    private val bottomContainer = LinearLayout(context).apply {
        orientation = VERTICAL
        gravity = Gravity.BOTTOM
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }
    private val buttonContainer = LinearLayout(context).apply {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(50, 1100, 50, 50)
        }
    }
    private val forgotButton = Button(context).apply {
        text = "Forgot password?"
        isAllCaps = false
        gravity = Gravity.CENTER
        setPadding(40, 10, 40, 10)
        background = createRoundedBackgroundButtonLupa()
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            weight = 2f
        }
        setTextColor(Color.WHITE)

        setOnClickListener {
            showPopupDialogFail()
        }
    }
    private val spacer = View(context).apply {
        layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
    }
    private val next2Button = Button(context).apply {
        text = "Next"
        isAllCaps = false
        gravity = Gravity.CENTER
        setPadding(40, 10, 40, 10)
        background = createRoundedBackgroundButton()
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            weight = 4f
        }
        setOnClickListener {
            showPopupDialog()
        }
    }

    private fun showPopupDialog() {
        val mDialog = Dialog(context)
        mDialog.setContentView(R.layout.popup_berhasil)
        mDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val buttonOk = mDialog.findViewById<Button>(R.id.buttonsucces)
        buttonOk.setOnClickListener {
            mDialog.dismiss()
            val intent = Intent(context, AwalActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
        mDialog.show()
    }

    private fun showPopupDialogFail() {
        val mDialog = Dialog(context)
        mDialog.setContentView(R.layout.popup_gagal)
        mDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val buttonOk = mDialog.findViewById<Button>(R.id.buttonFailed)
        buttonOk.setOnClickListener {
            mDialog.dismiss()
        }
        mDialog.show()
    }

    init{
        orientation = VERTICAL
        setBackgroundColor(resources.getColor(R.color.black, null))

        addView(iconX)
        addView(textUtama, createLayoutParams())
        addView(usernameField, createLayoutParams().apply {
            setPadding(50, 0, 50, 0)
        })
        addView(passwordField, createLayoutParams().apply {
            setPadding(50, 0, 50, 0)
        })

        buttonContainer.addView(forgotButton)
        buttonContainer.addView(spacer)
        buttonContainer.addView(next2Button)
        bottomContainer.addView(buttonContainer)
        addView(bottomContainer)
    }

    private fun createLayoutParams(): LayoutParams {
        return LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(20, 20, 20, 20)
        }
    }
    private fun createRoundedBackground(): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.GRAY)
            setStroke(2, Color.GRAY)
            cornerRadius = 10f
        }
    }
    private fun createRoundedBackground2(): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.BLACK)
            setStroke(3, Color.BLUE)
            cornerRadius = 10f
        }
    }
    private fun createRoundedBackgroundButton(): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.WHITE)
            setStroke(2, Color.GRAY)
            cornerRadius = 100f
        }
    }
    private fun createRoundedBackgroundButtonLupa(): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.BLACK)
            setStroke(3, Color.WHITE)
            cornerRadius = 100f
        }
    }
}