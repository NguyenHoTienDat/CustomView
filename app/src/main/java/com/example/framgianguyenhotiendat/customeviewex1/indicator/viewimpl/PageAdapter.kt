package com.example.framgianguyenhotiendat.customeviewex1.indicator.viewimpl

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter

class PageAdapter(var fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> FragmentOne()
            1 -> FragmentTwo()
            else -> FragmentThree()
        }
    }

    override fun getCount() = 3


}