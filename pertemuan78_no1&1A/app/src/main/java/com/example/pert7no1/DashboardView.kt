package com.example.pert7no1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.pert7no1.R

class DashboardView (context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs){
    private val iconX = ImageView(context).apply {
        setImageResource(R.drawable.icon_x)
        layoutParams = LayoutParams(100, 100).apply {
            setMargins(0, 50,0, 0)
            orientation = VERTICAL
            gravity = Gravity.TOP
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }
    private val textAtas = TextView(context).apply {
        text = "Happening now\nJoin today."
        gravity = Gravity.START
        setTypeface(null, Typeface.BOLD)
        textSize = 40f
        setPadding(140, 450, 80, 0)
        setTextColor(resources.getColor(R.color.white, null))
    }
    private val googleButton = Button(context).apply {
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
        background = createRoundedBackground()
    }
    private val textAtau = TextView(context).apply {
        textSize = 12f
        gravity = Gravity.CENTER
        setTextColor(ContextCompat.getColor(context, R.color.grey_700))

        val text = " or "
        val spannable = SpannableString("────────────────── $text ──────────────────")
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.grey_700)), 0, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setText(spannable)
    }
    private val createButton = Button(context).apply {
        text = "Create account"
        isAllCaps = false
        gravity = Gravity.CENTER
        setPadding(20, 10, 20, 10)
        setTextColor(Color.WHITE)
        background = GradientDrawable().apply {
            setColor(ContextCompat.getColor(context, R.color.twitter_blue))
            cornerRadius = 100f
        }
    }
    private val textPrivasi = TextView(context).apply {
        textSize = 14f
        gravity = Gravity.START
        setPadding(80, 0, 80, 0)
        setTextColor(ContextCompat.getColor(context, R.color.grey_700))

        val fullText = "By signing up, you agree to the Terms of Service and Privacy Policy, including Cookie Use."
        val spannable = SpannableString(fullText)
        val highlightColor = ContextCompat.getColor(context, R.color.twitter_blue)

        val termsStart = fullText.indexOf("Terms of Service")
        val privacyStart = fullText.indexOf("Privacy Policy")
        val cookieStart = fullText.indexOf("Cookie Use")

        if (termsStart != -1) {
            spannable.setSpan(
                ForegroundColorSpan(highlightColor),
                termsStart,
                termsStart + "Terms of Service".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        if (privacyStart != -1) {
            spannable.setSpan(
                ForegroundColorSpan(highlightColor),
                privacyStart,
                privacyStart + "Privacy Policy".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        if (cookieStart != -1) {
            spannable.setSpan(
                ForegroundColorSpan(highlightColor),
                cookieStart,
                cookieStart + "Cookie Use".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        text = spannable
    }

    private val textSign = TextView(context).apply {
        textSize = 16f
        gravity = Gravity.START
        setPadding(200, 48, 80, 0)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        movementMethod = LinkMovementMethod.getInstance()

        val fullText = "Already have an account? Sign in"
        val spannable = SpannableString(fullText)
        val highlightColor = ContextCompat.getColor(context, R.color.twitter_blue)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(context, login_activity::class.java)
                context.startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = highlightColor
                ds.isUnderlineText = false
            }
        }

        val start = fullText.indexOf("Sign in")
        if (start != -1) {
            spannable.setSpan(clickableSpan, start, start + "Sign in".length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        text = spannable
    }

    init {
        setBackgroundColor(resources.getColor(R.color.black, null))

        addView(iconX)
        addView(textAtas, createLayoutParams())
        addView(googleButton, createLayoutParams().apply {
            setMargins(80, 400, 80, 0)
        })
        addView(textAtau, createLayoutParams())
        addView(createButton, createLayoutParams().apply {
            setMargins(80, 0, 80, 0)
        })
        addView(textPrivasi, createLayoutParams())
        addView(textSign, createLayoutParams())
    }

    private fun createLayoutParams(): LayoutParams {
        return LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(20, 20, 20, 20)
        }
    }
    private fun createRoundedBackground(): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.WHITE)
            setStroke(2, Color.GRAY)
            cornerRadius = 100f
        }
    }
}