package com.example.pert7no1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PostView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val xBounds = Rect(40, 30, 100, 100)

    private val paint = Paint().apply {
        color = Color.WHITE
        textSize = 60f
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Gambar huruf X (tombol close)
        canvas.drawText("X", 40f, 90f, paint)

        // Gambar lingkaran (optional dekoratif)
        paint.color = Color.DKGRAY
        canvas.drawCircle(90f, 220f, 60f, paint)
        paint.color = Color.WHITE // Kembalikan warna huruf
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val x = event.x.toInt()
            val y = event.y.toInt()

            if (xBounds.contains(x, y)) {
                // Kembali ke AwalActivity saat "X" ditekan
                val intent = Intent(context, AwalActivity::class.java)
                context.startActivity(intent)
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}
