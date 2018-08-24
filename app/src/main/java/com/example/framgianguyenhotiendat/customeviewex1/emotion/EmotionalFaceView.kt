package com.example.framgianguyenhotiendat.customeviewex1.emotion

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.framgianguyenhotiendat.customeviewex1.R

class EmotionalFaceView constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // Some colors for the face background, eyes and mouth.
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    // Face border width in pixels
    private var borderWidth = DEFAULT_BORDER_WIDTH
    // View size in pixels
    private var size = 0

    private val mouthPath = Path()

    var happinessState = HAPPY
        set(state) {
            field = state
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView).apply {
            happinessState = this.getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
            faceColor = this.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
            eyesColor = this.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
            mouthColor = this.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
            borderColor = this.getColor(R.styleable.EmotionalFaceView_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = this.getDimension(R.styleable.EmotionalFaceView_borderWidth, DEFAULT_BORDER_WIDTH)

            recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            drawFaceBackground(this)
            drawEyes(this)
            drawMouth(this)
        }
    }

    private fun drawFaceBackground(canvas: Canvas) {

        canvas.run {
            //draw face
            drawCircle(size / 2f, size / 2f, size / 2f, paint.apply {
                color = faceColor
                style = Paint.Style.FILL
            })

            //draw border

            drawCircle(size / 2f, size / 2f, size / 2f - borderWidth / 2, paint.apply {
                color = borderColor
                style = Paint.Style.STROKE
                strokeWidth = borderWidth
            })
        }
    }

    private fun drawEyes(canvas: Canvas) {

        canvas.run {
            drawOval(RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f), paint.apply {
                color = eyesColor
                style = Paint.Style.FILL
            })

            drawOval(RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f), paint)
        }
    }

    private fun drawMouth(canvas: Canvas) {

        mouthPath.reset()

        canvas.drawPath(mouthPath.apply {
            moveTo(size * 0.22f, size * 0.7f)
            if (happinessState == HAPPY) {
                // Happy mouth path
                mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
                mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
            } else {
                // Sad mouth path
                mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
                mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
            }
        }, paint.apply {
            color = mouthColor
            style = Paint.Style.FILL
        })

    }

}