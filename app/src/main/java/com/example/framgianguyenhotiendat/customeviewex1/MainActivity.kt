package com.example.framgianguyenhotiendat.customeviewex1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_down_padding.setOnClickListener { view_custom.downPadding(100) }
        button_up_padding.setOnClickListener { view_custom.upPadding(100) }
        button_swap_color.setOnClickListener { view_custom.swapColor() }
    }
}
