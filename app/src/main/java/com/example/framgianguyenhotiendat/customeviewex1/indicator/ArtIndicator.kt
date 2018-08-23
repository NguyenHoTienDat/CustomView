package com.example.framgianguyenhotiendat.customeviewex1.indicator

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.framgianguyenhotiendat.customeviewex1.R

class ArtIndicator constructor(context: Context, attrs: AttributeSet)
    : View(context, attrs), IndicatorContract, ViewPager.OnPageChangeListener {

    companion object {

        private const val DEF_ANIMATE_DURATION = 200
        private const val DEF_RADIUS_SELECTED = 20
        private const val DEF_RADIUS_UNSELECTED = 15
        private const val DEF_DOT_DISTANCE = 40
    }

    init {
        initView(context, attrs)
    }

    private lateinit var viewPager: ViewPager

    private var animateDuration = DEF_ANIMATE_DURATION
    private var radiusSelected = DEF_RADIUS_SELECTED
    private var radiusUnSelected = DEF_RADIUS_UNSELECTED
    private var dotDistance = DEF_DOT_DISTANCE

    private var currentDotSelected: Int = 0
    private var previousDotSelected: Int = 0

    private var colorSelected: Int = 0
    private var colorUnSelected: Int = 0

    private lateinit var animatorZoomIn: ValueAnimator
    private lateinit var animatorZoomOut: ValueAnimator

    private var dots = mutableListOf<Dot>()

    /**
     * Fun in view lifecycle
     * Here, view is calculated done
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        var yCenter = height / 2
        var dis = dotDistance + 2 * radiusUnSelected

        var firstXCenter = (getWidth() / 2) - ((dots.size - 1) * dis / 2)

        for ((pos, dot) in dots.withIndex()) {
            dot.apply {
                Log.d("xxxx", "run $pos")
                currentRadius = if (pos == currentDotSelected) radiusSelected else radiusUnSelected

//                setCenterPointCoordinator(if (pos == 0) firstXCenter.toFloat()
//                else (firstXCenter + dis * pos).toFloat(), yCenter.toFloat())

                setCenterPointCoordinator((20 * pos + 50).toFloat(),yCenter.toFloat())
                setDotAlpha(if (pos == currentDotSelected) 255 else radiusUnSelected * 255 / radiusSelected)
                setDotColor(if (pos == currentDotSelected) colorSelected else colorUnSelected)
            }
        }
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var designHeight = 2 * radiusSelected

        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(
                when (MeasureSpec.getMode(widthMeasureSpec)) {
                    MeasureSpec.AT_MOST -> widthSize
                    MeasureSpec.EXACTLY -> widthSize
                    else -> 0
                },

                when (MeasureSpec.getMode(heightMeasureSpec)) {
                    MeasureSpec.AT_MOST -> Math.min(designHeight, heightSize)
                    MeasureSpec.EXACTLY -> heightSize
                    else -> designHeight
                }
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            for (dot in dots) {
                dot.draw(canvas)
            }
        }
    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {
        previousDotSelected = currentDotSelected
        colorUnSelected = p0

//        //case swipe back
//        if (currentDotSelected == previousDotSelected) previousDotSelected = currentDotSelected++
//
//        AnimatorSet().apply {
//            duration = animateDuration.toLong()
//
//            animatorZoomIn = ValueAnimator.ofInt(radiusUnSelected, radiusSelected).apply {
//                addUpdateListener { it -> changeRadius(currentDotSelected, if (it.animatedValue is Int) it.animatedValue as Int else null) }
//            }
//
//            animatorZoomOut = ValueAnimator.ofInt(radiusSelected, radiusUnSelected).apply {
//                addUpdateListener { it -> changeRadius(previousDotSelected, if (it.animatedValue is Int) it.animatedValue as Int else null) }
//            }
//
//            play(animatorZoomIn).with(animatorZoomOut)
//            start()
//        }
    }

    private fun changeRadius(dotSelectedPos: Int, newRadius: Int?) {
        newRadius?.let { newRas ->
            if (dots[dotSelectedPos].currentRadius != newRas) {
                dots[dotSelectedPos].apply {
                    currentRadius = newRas
                    alpha = (newRas * 255 / radiusSelected).toFloat()
                    invalidate()
                }
            }
        }
    }

    override fun setViewPager(viewPager: ViewPager) {
        this@ArtIndicator.viewPager = viewPager
        this@ArtIndicator.viewPager.apply {
            addOnPageChangeListener(this@ArtIndicator)
            initDot(adapter?.count ?: 0)
            onPageSelected(0)
        }
    }

    override fun setRadiusSelected(radius: Int) {
        this@ArtIndicator.radiusSelected = radius
    }

    override fun setRadiusUnSelected(radius: Int) {
        this@ArtIndicator.radiusUnSelected = radius
    }

    override fun setDotDistance(distance: Int) {
        this@ArtIndicator.dotDistance = distance
    }

    override fun setAnimateDuration(duration: Int) {
        this@ArtIndicator.animateDuration = duration
    }

    /**
     * fun init
     * We get all attr in attrs.xml here
     */
    private fun initView(context: Context, attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.ArtIndicator).apply {
            this@ArtIndicator.dotDistance = this.getInt(R.styleable.ArtIndicator_mi_distance_dot, DEF_DOT_DISTANCE)

            this@ArtIndicator.radiusUnSelected = this.getDimensionPixelSize(R.styleable.ArtIndicator_mi_color_unselected, DEF_RADIUS_UNSELECTED)
            this@ArtIndicator.radiusSelected = this.getDimensionPixelSize(R.styleable.ArtIndicator_mi_color_selected, DEF_RADIUS_SELECTED)

            this@ArtIndicator.colorSelected = this.getColor(R.styleable.ArtIndicator_mi_color_selected, Color.parseColor("#ffffff"))
            this@ArtIndicator.colorUnSelected = this.getColor(R.styleable.ArtIndicator_mi_color_unselected, Color.parseColor("#ffffff"))
            recycle()
        }

    }

    /**
     * This fun create the number of dot map with received viewpager
     * We call this fun in setViewPager above
     * Note that we only create the number of dot.
     * Calculate center and radius demand on view size. But we don't sure view is calculated or not.
     * So we excute that in onLayout, where view size is calculated surely
     */
    @Throws(PageException::class)
    private fun initDot(count: Int) {
        if (count < 2) throw PageException()

        for (i in 1..count) {
            dots.add(Dot())
        }
    }

}
