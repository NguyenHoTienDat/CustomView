package com.example.framgianguyenhotiendat.customeviewex1.indicator.viewimpl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.framgianguyenhotiendat.customeviewex1.R
import com.example.framgianguyenhotiendat.customeviewex1.R.id.art_indicator
import kotlinx.android.synthetic.main.activity_imp_indicator.*

class ImpIndicatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imp_indicator)

        pager_test.apply {
            adapter = PageAdapter(supportFragmentManager)
            art_indicator.setViewPager(this)
        }
    }
}
