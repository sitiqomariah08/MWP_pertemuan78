package com.example.pert7no1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat

class AwalView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    val radius = 140f
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val paintCircle = Paint(Paint.ANTI_ALIAS_FLAG)
    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val normalTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bioTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val linkTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    lateinit var vectorDrawable: Drawable
    lateinit var vectorDrawable2: Drawable
    lateinit var vectorDrawable3: Drawable
    lateinit var vectorDrawable4: Drawable
    lateinit var vectorDrawable5: Drawable
    lateinit var vectorDrawable6: Drawable
    lateinit var vectorDrawablePlus: Drawable
    val buttonRect = RectF()
    private var followersBounds = Rect()
    private var jmlFollowersBounds = Rect()
    private val backButtonBounds = RectF()
    private val moreButtonBounds = RectF()
    private val fabButtonBounds = RectF()

    private val tabTitles = listOf("Posts", "Replies", "Highlights", "Articles", "Media")
    private val tabRects = mutableListOf<RectF>()
    private var selectedTabIndex = 0

    private var showPopup = false
    private val popupRect = RectF()
    private val popupItems = listOf("Go Live", "Spaces", "Photos", "Post")
    private val popupItemRects = mutableListOf<RectF>()

    private val popupPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    init {
        textPaint.apply {
            color = Color.BLACK
            textSize = 50f
            textAlign = Paint.Align.LEFT
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
        normalTextPaint.apply {
            color = Color.DKGRAY
            textSize = 40f
            textAlign = Paint.Align.LEFT
        }
        bioTextPaint.apply {
            color = Color.BLACK
            textSize = 40f
            textAlign = Paint.Align.LEFT
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
        linkTextPaint.apply {
            color = Color.parseColor("#87CEFA")
            textSize = 40f
            textAlign = Paint.Align.LEFT
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
        vectorDrawable = ContextCompat.getDrawable(context, R.drawable.baseline_badge_24)!!
        vectorDrawable2 = ContextCompat.getDrawable(context, R.drawable.baseline_add_location_24)!!
        vectorDrawable3 = ContextCompat.getDrawable(context, R.drawable.baseline_calendar_month_24)!!
        vectorDrawable4 = ContextCompat.getDrawable(context, R.drawable.baseline_link_24)!!
        vectorDrawable5 = ContextCompat.getDrawable(context, R.drawable.baseline_arrow_back_241)!!
        vectorDrawable6 = ContextCompat.getDrawable(context, R.drawable.baseline_more_vert_24)!!
        vectorDrawablePlus = ContextCompat.getDrawable(context, R.drawable.baseline_add_24)!!
        isClickable = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.BLUE
        canvas?.drawRect(0f, 0f, width - 0f, height - 1800f, paint)

        val backX = 45f
        val backY = 40f
        val backWidth = 70
        val backHeight = 70
        vectorDrawable5.setBounds(backX.toInt(), backY.toInt(), backX.toInt() + backWidth, backY.toInt() + backHeight)
        vectorDrawable5.draw(canvas)
        backButtonBounds.set(backX, backY, backX + backWidth, backY + backHeight)

        val moreX = width - 85f
        val moreY = 40f
        val moreWidth = 70
        val moreHeight = 70
        vectorDrawable6.setBounds(moreX.toInt(), moreY.toInt(), moreX.toInt() + moreWidth, moreY.toInt() + moreHeight)
        vectorDrawable6.draw(canvas)
        moreButtonBounds.set(moreX, moreY, moreX + moreWidth, moreY + moreHeight)

        paintCircle.color = Color.GREEN
        val circleX = radius + 30f
        val circleY = height - radius - 1700f
        canvas?.drawCircle(circleX, circleY, radius, paintCircle)

        val textY = circleY + radius + 60f
        val textX = circleX - radius
        canvas.drawText("riaa", textX, textY, textPaint)

        val textY2 = textY + 55f
        canvas.drawText("@riaamariaa1730", textX, textY2, normalTextPaint)

        val textY3 = textY2 + 70f
        canvas.drawText("lil meow", textX, textY3, bioTextPaint)

        val badgeX = textX
        val badgeY = textY3 + 40f
        val badgeWidth = 50
        val badgeHeight = 50
        vectorDrawable.setBounds(badgeX.toInt(), badgeY.toInt(), badgeX.toInt() + badgeWidth, badgeY.toInt() + badgeHeight)
        vectorDrawable.draw(canvas)

        val verY4 = badgeX + 70f
        val horiY4 = textY3 + 80f
        canvas.drawText("Advertising & Marketing Agency", verY4, horiY4, normalTextPaint)

        val locX = textX
        val locY = badgeY + badgeHeight + 20f
        val locWidth = 50
        val locHeight = 50
        vectorDrawable2.setBounds(locX.toInt(), locY.toInt(), locX.toInt() + locWidth, locY.toInt() + locHeight)
        vectorDrawable2.draw(canvas)

        val loctextX = locX + 70f
        val loctextY = horiY4 + 70f
        canvas.drawText("Surabaya, Indonesia", loctextX, loctextY, normalTextPaint)

        val linkX = loctextX + 390f
        val linkY = locY
        val linkWidth = 50
        val linkHeight = 50
        vectorDrawable4.setBounds(linkX.toInt(), linkY.toInt(), linkX.toInt() + linkWidth, linkY.toInt() + linkHeight)
        vectorDrawable4.draw(canvas)

        val linktextX = linkX + 70f
        val linktextY = linkY + 40f
        canvas.drawText("Https://marimariaa..", linktextX, linktextY, linkTextPaint)

        val calX = textX
        val calY = locY + locHeight + 20f
        val calWidth = 50
        val calHeight = 50
        vectorDrawable3.setBounds(calX.toInt(), calY.toInt(), calX.toInt() + calWidth, calY.toInt() + calHeight)
        vectorDrawable3.draw(canvas)

        val caltextX = calX + 70f
        val caltextY = loctextY + 70f
        canvas.drawText("Joined Agustus, 2023", caltextX, caltextY, normalTextPaint)

        val jmlFollowingX = circleX - radius
        val jmlFollowingY = caltextY + 70f
        canvas.drawText("2134", jmlFollowingX, jmlFollowingY, bioTextPaint)

        val FollowingX = jmlFollowingX + 115f
        val FollowingY = caltextY + 70f
        canvas.drawText("Following", FollowingX, FollowingY, normalTextPaint)

        val jmlFollowersX = FollowingX + 250f
        val jmlFollowersY = caltextY + 70f
        canvas.drawText("23,4K", jmlFollowersX, jmlFollowersY, bioTextPaint)
        bioTextPaint.getTextBounds("23,4K", 0, "23,4K".length, jmlFollowersBounds)
        jmlFollowersBounds.offset(jmlFollowersX.toInt(), jmlFollowersY.toInt())

        val FollowersX = jmlFollowersX + 115f
        val FollowersY = caltextY + 70f
        canvas.drawText("Followers", FollowersX, FollowersY, normalTextPaint)
        normalTextPaint.getTextBounds("Followers", 0, "Followers".length, followersBounds)
        followersBounds.offset(FollowersX.toInt(), FollowersY.toInt())

        val btnEditX = width / 4f + 400f
        val btnEditY = height - radius - 1620f
        val btnEditWidth = 350f
        val btnEditHeight = 100f

        buttonRect.set(btnEditX, btnEditY, btnEditX + btnEditWidth, btnEditY + btnEditHeight)

        paint.color = Color.LTGRAY
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f

        canvas.drawRoundRect(buttonRect, 50f, 50f, paint)

        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        paint.textSize = 45f

        paint.color = Color.BLACK
        paint.textSize = 45f
        val buttonText = "Edit Profile"
        val textWidth = paint.measureText(buttonText)
        canvas.drawText(buttonText, buttonRect.centerX() - textWidth / 2, buttonRect.centerY() + 15f, paint)

        val fabSize = 150f
        val fabMargin = 50f
        val fabX = width - fabSize - fabMargin
        val fabY = height - fabSize - fabMargin

        paint.color = Color.parseColor("#1DA1F2")
        paint.style = Paint.Style.FILL
        canvas.drawCircle(fabX + fabSize/2, fabY + fabSize/2, fabSize/2, paint)

        val plusSize = 70
        vectorDrawablePlus.setBounds(
            (fabX + fabSize/2 - plusSize/2).toInt(),
            (fabY + fabSize/2 - plusSize/2).toInt(),
            (fabX + fabSize/2 + plusSize/2).toInt(),
            (fabY + fabSize/2 + plusSize/2).toInt()
        )
        vectorDrawablePlus.draw(canvas)

        fabButtonBounds.set(fabX, fabY, fabX + fabSize, fabY + fabSize)

        drawTabMenu(canvas)

        if (showPopup) {
            drawPopup(canvas)
        }
    }

    private fun drawTabMenu(canvas: Canvas) {
        val tabHeight = 60f
        val tabStartY = height - 1800f + paint.measureText("@riaamariaa1730") + 270f

        paint.textSize = 40f
        val totalWidth = width.toFloat()
        val totalTabWidth = paint.measureText(tabTitles.joinToString("")) + (tabTitles.size * 80f)
        val startX = (totalWidth - totalTabWidth) / 2

        tabRects.clear()

        var currentX = startX
        tabTitles.forEachIndexed { index, title ->
            val textWidth = paint.measureText(title)
            val tabWidth = textWidth + 80f

            paint.typeface = if (index == selectedTabIndex) {
                Typeface.DEFAULT_BOLD
            } else {
                Typeface.DEFAULT
            }

            paint.color = Color.BLACK
            canvas.drawText(
                title,
                currentX + 40f,
                tabStartY + tabHeight / 2 + 15f,
                paint
            )

            if (index == selectedTabIndex) {
                paint.color = Color.parseColor("#1DA1F2")
                paint.strokeWidth = 3f
                canvas.drawLine(
                    currentX + 20f,
                    tabStartY + tabHeight - 0f,
                    currentX + tabWidth - 20f,
                    tabStartY + tabHeight - 0f,
                    paint
                )
                paint.strokeWidth = 1f
            }

            tabRects.add(RectF(
                currentX,
                tabStartY,
                currentX + tabWidth,
                tabStartY + tabHeight
            ))

            currentX += tabWidth
        }
    }

    private fun drawPopup(canvas: Canvas) {
        val popupWidth = width * 0.85f
        val popupHeight = 450f

        val popupX = (width - popupWidth) / 2
        val popupY = (height - popupHeight) / 2

        popupRect.set(popupX, popupY, popupX + popupWidth, popupY + popupHeight)

        popupPaint.apply {
            color = Color.WHITE
            style = Paint.Style.FILL
            setShadowLayer(25f, 0f, 5f, Color.parseColor("#80000000"))
        }

        canvas.drawRoundRect(popupRect, 30f, 30f, popupPaint)

        val titlePaint = Paint().apply {
            color = Color.BLACK
            textSize = 45f
            typeface = Typeface.DEFAULT_BOLD
        }

        canvas.drawText(
            "Pilih Opsi",
            popupRect.centerX() - titlePaint.measureText("Pilih Opsi")/2,
            popupY + 70f,
            titlePaint
        )

        val linePaint = Paint().apply {
            color = Color.LTGRAY
            strokeWidth = 2f
        }
        canvas.drawLine(
            popupX + 30f,
            popupY + 100f,
            popupX + popupWidth - 30f,
            popupY + 100f,
            linePaint
        )

        val itemPaint = Paint().apply {
            color = Color.BLACK
            textSize = 40f
        }

        popupItemRects.clear()
        val iconPaint = Paint().apply {
            textSize = 45f
        }

        popupItems.forEachIndexed { index, item ->
            val itemY = popupY + 160f + (index * 80f)

            canvas.drawText(
                when(index) {
                    0 -> "🔴"  // Live
                    1 -> "🎙️"  // Spaces
                    2 -> "📷"  // Photos
                    3 -> "✏️"  // Post
                    else -> ""
                },
                popupX + 50f,
                itemY,
                iconPaint
            )

            canvas.drawText(
                item,
                popupX + 120f,
                itemY,
                itemPaint
            )

            popupItemRects.add(
                RectF(
                    popupX,
                    itemY - 50f,
                    popupX + popupWidth,
                    itemY + 30f
                )
            )
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (followersBounds.contains(x.toInt(), y.toInt()) ||
                    jmlFollowersBounds.contains(x.toInt(), y.toInt())) {
                    val intent = Intent(context, FollowersActivity::class.java)
                    context.startActivity(intent)
                    return true
                }
                if (backButtonBounds.contains(x, y)) {
                    return true
                }
                if (moreButtonBounds.contains(x, y)) {
                    return true
                }
                if (fabButtonBounds.contains(x, y)) {
                    showPopup = true
                    invalidate()
                    return true
                }
                if (showPopup && !popupRect.contains(x, y)) {
                    showPopup = false
                    invalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                if (buttonRect.contains(x, y)) {
                    Toast.makeText(context, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
                    return true
                }
                if (backButtonBounds.contains(x, y)) {
                    Toast.makeText(context, "Back button clicked", Toast.LENGTH_SHORT).show()
                    return true
                }
                if (moreButtonBounds.contains(x, y)) {
                    Toast.makeText(context, "More options clicked", Toast.LENGTH_SHORT).show()
                    return true
                }
                if (fabButtonBounds.contains(x, y)) {

                    return true
                }
                if (showPopup) {
                    popupItemRects.forEachIndexed { index, rect ->
                        if (rect.contains(x, y)) {
                            when (index) {
                                0 -> Toast.makeText(context, "Go Live selected", Toast.LENGTH_SHORT).show()
                                1 -> Toast.makeText(context, "Spaces selected", Toast.LENGTH_SHORT).show()
                                2 -> Toast.makeText(context, "Photos selected", Toast.LENGTH_SHORT).show()
                                3 -> {

                                    val intent = Intent(context, PostActivity::class.java)
                                    context.startActivity(intent)
                                }
                            }
                            showPopup = false
                            invalidate()
                            return true
                        }
                    }
                }

            }
        }
        return super.onTouchEvent(event)
    }
}
