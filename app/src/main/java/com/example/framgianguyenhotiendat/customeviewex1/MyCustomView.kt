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

class MyCustomView constructor(context: Context) : View(context) {

    private lateinit var rect: Rect
    private lateinit var paint: Paint

    constructor(context: Context, @Nullable attrs: AttributeSet) : this(context) {
        init(attrs)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : this(context) {
        init(attrs)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : this(context) {
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
                left = 0
                top = 0
                right = 100
                bottom = 100
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

    fun upPadding(padding : Int) {

    }

    fun downPadding(padding: Int) {

    }
}
