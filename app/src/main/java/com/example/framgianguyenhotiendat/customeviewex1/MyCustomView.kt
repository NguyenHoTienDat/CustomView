package com.example.framgianguyenhotiendat.customeviewex1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View
import java.util.*

class MyCustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var rect: Rect
    private lateinit var paint: Paint

    var padding: Int = 0

    init {
        init(attrs)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            drawRect(rect.apply {
                left = 50
                top = 50
                right = width - padding
                bottom = height - padding
            }, paint)
        }
    }

    private fun init(set: AttributeSet?) {
        paint = Paint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
        }

        rect = Rect()

        set?.run {
            context.obtainStyledAttributes(set, R.styleable.MyCustomView).run {
                paint.color = this@run.getColor(R.styleable.MyCustomView_piv_shape_color, Color.GREEN)
                recycle()
            }

        }
    }

    fun swapColor() {
        when (Random().nextBoolean()) {
            true -> paint.color = Color.MAGENTA
            else -> paint.color = Color.RED
        }
        postInvalidate()
    }

    fun upPadding(padding: Int) {
        this@MyCustomView.padding += padding
        postInvalidate()
    }

    fun downPadding(padding: Int) {
        this@MyCustomView.padding -= padding
        postInvalidate()
    }
}
