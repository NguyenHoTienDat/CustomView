package com.example.framgianguyenhotiendat.customeviewex1.indicator

import android.support.v4.view.ViewPager

interface IndicatorContract {

    @Throws(PageException::class)
    fun setViewPager(viewPager: ViewPager)

    fun setRadiusSelected(radius: Int)

    fun setRadiusUnSelected(radius: Int)

    fun setDotDistance(distance: Int)

    fun setAnimateDuration(duration: Int)
}