package com.example.pert7no1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.pert7no1.R

class LoginView (context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val iconX = ImageView(context).apply {
        setImageResource(R.drawable.icon_x)
        layoutParams = LinearLayout.LayoutParams(200, 100).apply {
            setPadding(30, 32, 30, 0)
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }
    private val textAwal = TextView(context).apply {
        text = "Sign in to X"
        gravity = Gravity.START
        setTypeface(null, Typeface.BOLD)
        textSize = 23f
        setPadding(5, 80, 30, 0)
        setTextColor(resources.getColor(R.color.white, null))
    }
    private val googleButton2 = Button(context).apply {
        text = "Sign in with Google"
        isAllCaps = false
        gravity = Gravity.CENTER
        setPadding(20, 8, 20, 10)

        val drawable = ContextCompat.getDrawable(context, R.drawable.google_color_svgrepo_com)
        drawable?.setBounds(0, 0, 50, 50)

        val spannable = SpannableString("  Sign in with Google")
        val imageSpan = ImageSpan(drawable!!, ImageSpan.ALIGN_BASELINE)
        spannable.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        text = spannable
        background = createRoundedBackgroundGoogle()
    }
    private val textAtau2 = TextView(context).apply {
        textSize = 12f
        gravity = Gravity.CENTER
        setTextColor(ContextCompat.getColor(context, R.color.grey_700))

        val text = " or "
        val spannable = SpannableString("────────────────── $text ──────────────────")
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.grey_700)), 0, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setText(spannable)
    }
    private val usernameField = EditText(context).apply {
        hint = "Phone, email, or username"
        setHintTextColor(Color.LTGRAY)
        setTextColor(Color.WHITE)
        setHighlightColor(Color.WHITE)
        setPadding(40, 50, 40, 40)
        background = createRoundedBackground()
        setSingleLine(true)
        ellipsize = TextUtils.TruncateAt.END
    }
    private val bottomContainer = LinearLayout(context).apply {
        orientation = VERTICAL
        gravity = Gravity.BOTTOM
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }
    private val buttonContainer = LinearLayout(context).apply {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(50, 100, 50, 50)
        }
    }
    private val bottonContainerr = LinearLayout(context).apply {
        orientation = VERTICAL
        gravity = Gravity.BOTTOM
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    private val ButtonLupa = Button(context).apply {
        text = "Forgot password?"
        isAllCaps = false
        gravity = Gravity.CENTER
        setPadding(30, 10, 30, 10)
        background = createRoundedBackgroundButtonLupa()
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            weight = 2f
        }
        setTextColor(Color.WHITE)
    }
    private val spacer = View(context).apply {
        layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
    }
    private val nextButton = Button(context).apply {
        text = "Next"
        isAllCaps = false
        gravity = Gravity.CENTER
        setPadding(10, 10, 10, 10)
        background = createRoundedBackgroundButton()
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            weight = 4f
        }
        setOnClickListener {
            val intent = Intent(context, nextActivity::class.java)
            context.startActivity(intent)
        }
    }

    init {
        orientation = VERTICAL
        setBackgroundColor(resources.getColor(R.color.black, null))

        addView(iconX)
        addView(textAwal, createLayoutParams())
        addView(googleButton2, createLayoutParams().apply {
            setMargins(35, 45, 35, 0)
        })
        addView(textAtau2, createLayoutParams().apply {
            setMargins(35, 27, 35, 0)
        })
        addView(usernameField, createLayoutParams().apply {
            setPadding(70, 0, 70, 0)
        })

        buttonContainer.addView(ButtonLupa)
        buttonContainer.addView(spacer)
        buttonContainer.addView(nextButton)
        bottomContainer.addView(buttonContainer)
        addView(bottomContainer)
    }

    private fun createLayoutParams(): LayoutParams {
        return LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(20, 20, 20, 20)
        }
    }
    private fun createRoundedBackgroundGoogle(): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.WHITE)
            setStroke(2, Color.GRAY)
            cornerRadius = 100f
        }
    }
    private fun createRoundedBackground(): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.BLACK)
            setStroke(3, Color.GRAY)
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
