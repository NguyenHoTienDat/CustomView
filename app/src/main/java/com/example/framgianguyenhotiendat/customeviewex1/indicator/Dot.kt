package com.example.framgianguyenhotiendat.customeviewex1.indicator

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF

/**
 * Draw dots of indicator
 */
data class Dot(var currentRadius: Int = 0) {

    private var paint: Paint = Paint().apply { isAntiAlias = true }
    private var centerOfDot: PointF = PointF()

    fun setDotColor(color: Int) {
        paint.color = color
    }

    fun setDotAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    fun setCenterPointCoordinator(x: Float, y: Float) {
        centerOfDot.set(x, y)
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(centerOfDot.x, centerOfDot.y, currentRadius.toFloat(), paint)
    }
}
